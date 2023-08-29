package com.example.calculadora;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText input_nombre_register;
    private EditText input_email_register;
    private EditText input_password_register;
    private EditText input_password_2_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        input_nombre_register = findViewById(R.id.input_nombre_register);
        input_email_register = findViewById(R.id.input_email_register);
        input_password_register = findViewById(R.id.input_password_register);
        input_password_2_register = findViewById(R.id.input_password_2_register);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // => METODO PENDIENTE DE HACER ===>>> updateUI(currentUser);
    }
    public void registerUser(View view){
        if(input_password_register.getText().toString().equals(input_password_2_register.getText().toString())){
            mAuth.createUserWithEmailAndPassword(input_email_register.getText().toString(), input_password_register.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                // => METODO PENDIENTE DE HACER ===>>> updateUI(user);

                                Toast.makeText(getApplicationContext(), "Usuario creado!" ,Toast.LENGTH_SHORT).show();

                                // la redireccion se hace con el contexto actual y a la activity que quiero ir,
                                // le mando un startActivity y un intento como parametro!
                                Intent i = new Intent(getApplicationContext(), calculadora.class);
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), task.getException().toString() ,Toast.LENGTH_LONG).show();

                                // Toast.makeText(getApplicationContext(), "Authentication failed." ,Toast.LENGTH_SHORT).show();
                                // => METODO PENDIENTE DE HACER ===>>> updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Las contraseñas no coinciden!", Toast.LENGTH_SHORT).show();
        }

    }
}