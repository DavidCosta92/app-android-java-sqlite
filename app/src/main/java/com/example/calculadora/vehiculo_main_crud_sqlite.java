package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class vehiculo_main_crud_sqlite extends AppCompatActivity {
    EditText etPatente, etMarca, etModelo, etPrecio;
    AdminSQLiteOpenHelper admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_main_crud_sqlite);
        etPatente = findViewById(R.id.etpatente);
        etMarca = findViewById(R.id.etmarca);
        etModelo = findViewById(R.id.etmodelo);
        etPrecio = findViewById(R.id.etprecio);
        admin = new AdminSQLiteOpenHelper(this, "base_datos_vehiculos", null, 1); // version es para las futuras modificaciones de la estructura de la bd
    }

    public void agregar(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();// abre la bd
        ContentValues registro = new ContentValues(); // crea un objeto que luego sera un nuevo registro
        registro.put("patente", etPatente.getText().toString());// agrego datos al objeto registro
        registro.put("marca", etMarca.getText().toString());
        registro.put("modelo", etModelo.getText().toString());
        registro.put("precio", etPrecio.getText().toString());
        bd.insert("vehiculos", null, registro);// inserta en tabla "vehiculos"
        etPatente.setText("");// limpio pantalla
        etMarca.setText("");
        etModelo.setText("");
        etPrecio.setText("");
        bd.close();// cierro conexion bd
        Toast.makeText(this,"Se agrego vehiculo", Toast.LENGTH_SHORT).show();
    }

    public void buscarPatente(View v){
        SQLiteDatabase bd = admin.getWritableDatabase(); // abre la bd
        String patente = etPatente.getText().toString();

        // este obj ejecuta la consulta a bd
        Cursor fila = bd.rawQuery("SELECT marca, modelo, precio FROM vehiculos WHERE patente = '"+patente+"'", null);

        if (fila.moveToFirst()){ // una vez que ejecuto consulta, voy al primer resultado, ya que patente es PK
            etMarca.setText(fila.getString(0)); // Obtengo el texto por indice de columna (o para marca, 1 para modelo, 2 para precio) y seteo las vistas con dichos valores
            etModelo.setText(fila.getString(1));
            etPrecio.setText(fila.getString(2));
        } else{
            Toast.makeText(this, "NO ECONTRAMOS VEHICULO CON ESA PATENTE", Toast.LENGTH_LONG).show();
            etMarca.setText("");// limpio pantalla
            etModelo.setText("");
            etPrecio.setText("");
        }
        bd.close(); // cierro conexion bd
    }

    public void borrarPorPatente(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String patente = etPatente.getText().toString();
        int cantidadFilas = bd.delete("vehiculos", "patente = '"+patente+"'", null);

        if(cantidadFilas == 1){
            Toast.makeText(this, "VEHICULO ELIMINADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
            etPatente.setText("");// limpio pantalla
            etMarca.setText("");
            etModelo.setText("");
            etPrecio.setText("");
        } else {
            Toast.makeText(this, "NO ECONTRAMOS VEHICULO CON ESA PATENTE", Toast.LENGTH_LONG).show();
        }

    }
    public void modificarPorPatente (View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String patente = etPatente.getText().toString();

        ContentValues registro = new ContentValues(); // crea un objeto que luego actualizara

        registro.put("marca", etMarca.getText().toString());// agrego datos al objeto registro
        registro.put("modelo", etModelo.getText().toString());
        registro.put("precio", etPrecio.getText().toString());
        int cantidadFilas = bd.update("vehiculos", registro, "patente = '"+patente+"'", null);
        if (cantidadFilas == 1){
            Toast.makeText(this, "VEHICULO ACTUALIZADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "NO ECONTRAMOS VEHICULO CON ESA PATENTE", Toast.LENGTH_LONG).show();
        }


    }
}