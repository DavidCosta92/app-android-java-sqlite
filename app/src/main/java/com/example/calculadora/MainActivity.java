package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToLogin(View view){
        Intent i = new Intent(this , inicioSessionActivity.class);
        startActivity(i);
    }
    public void goToRegister(View view){
        Intent i = new Intent(this , RegistrarUsuarioActivity.class);
        startActivity(i);

    }
    public void goToAgregarVehiculo(View view){
        Intent i = new Intent(this , vehiculo_main_crud_sqlite.class);
        startActivity(i);

    }




}