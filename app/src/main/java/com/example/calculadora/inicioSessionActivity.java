package com.example.calculadora;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

public class inicioSessionActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText input_email_login;
    private EditText input_password_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_session);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        input_email_login = findViewById(R.id.input_email_login);
        input_password_login = findViewById(R.id.input_password_login);
    }

    public void loginUser(View view){
        mAuth.signInWithEmailAndPassword(input_email_login.getText().toString(), input_password_login.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // => METODO PENDIENTE DE HACER ===>>> updateUI(user);

                            Toast.makeText(getApplicationContext(), "Login correcto!" ,Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), calculadora.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // => METODO PENDIENTE DE HACER ===>>> updateUI(null);
                        }
                    }
                });
    }
    @Override
    public void onStart(){
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Name, email address, and profile photo Url
            // String name = user.getDisplayName();
            // String email = user.getEmail();
            // Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            // boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            // String uid = user.getUid();
        }
        // => METODO PENDIENTE DE HACER ===>>> updateUI(currentUser);
    }

}