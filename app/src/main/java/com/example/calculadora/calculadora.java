package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class calculadora extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btn_suma;
    private Button btn_resta;
    private Button btn_division;
    private Button btn_multiplicacion;
    private FloatingActionButton btn_agregarVehiculo;

    private TextView text_logued_user;
    private TextView textView_respuesta;
    private EditText editText_numero1;
    private EditText editText_numero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // esto es la parte de calculadora
        textView_respuesta = findViewById(R.id.respuesta);
        editText_numero1 = findViewById(R.id.numero1);
        editText_numero2 = findViewById(R.id.numero2);

        text_logued_user = findViewById(R.id.text_logued_user);
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
            String email = currentUser.getEmail();
            text_logued_user.setText(email);
        }else{
            text_logued_user.setText("USUARIO NO LOGUEADOOO");
        }
        
        btn_suma = findViewById( R.id.btn_suma);
        btn_suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_respuesta.setText(suma(Double.parseDouble(editText_numero1.getText().toString()) , Double.parseDouble(editText_numero2.getText().toString()))+"");
            }
        });

        btn_resta = findViewById( R.id.btn_resta);
        btn_resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_respuesta.setText(resta(Double.parseDouble(editText_numero1.getText().toString()) , Double.parseDouble(editText_numero2.getText().toString()))+"");
            }
        });
        btn_division = findViewById( R.id.btn_division);
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_respuesta.setText(division(Double.parseDouble(editText_numero1.getText().toString()) , Double.parseDouble(editText_numero2.getText().toString()))+"");
            }
        });
        btn_multiplicacion = findViewById( R.id.btn_multiplicacion);
        btn_multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_respuesta.setText(multiplicacion(Double.parseDouble(editText_numero1.getText().toString()) , Double.parseDouble(editText_numero2.getText().toString()))+"");
            }
        });
    }


    // ESTO ES PARA CALCULADORA, MIGRAR A SU PROPIA ACTIVITY
    public double suma (double a, double b){
        return a + b;
    }
    public double resta (double a, double b){
        return a - b;
    }
    public double division (double a, double b){
        if (b !=0){
            return a / b;
        }else{
            return 0;
        }
    }
    public double multiplicacion (double a, double b){
        return a * b;
    }
}