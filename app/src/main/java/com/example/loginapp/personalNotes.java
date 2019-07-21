package com.example.loginapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.example.loginapp.APPSQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;




public class personalNotes extends AppCompatActivity {

    private EditText titulo, ubicacion,inicio,fin,asistente,codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_notes);

        titulo= (EditText)findViewById(R.id.txtTitulo);
        ubicacion= (EditText)findViewById(R.id.txtUbicacion);
        inicio= (EditText)findViewById(R.id.txtInicio);
        fin= (EditText)findViewById(R.id.txtFin);
        asistente= (EditText)findViewById(R.id.txtAsist);
        codigo= (EditText)findViewById(R.id.txtcodigo);

    }

    public void buscar(View view){
        APPSQLiteOpenHelper con = new APPSQLiteOpenHelper(this,"notas",null,1);
        SQLiteDatabase sql = con.getWritableDatabase();

        String cod= codigo.getText().toString();
        if(!cod.isEmpty()){

            Log.i(cod, "ENTRO********");


            Cursor fila = sql.rawQuery
                    ("select titulo,ubicacion, fecha_inicio, fecha_final, asistentes from notas where _id=" + cod, null);

            if(fila.moveToFirst()){
                titulo.setText(fila.getString(0));
                ubicacion.setText(fila.getString(1));
                inicio.setText(fila.getString(2));
                fin.setText(fila.getString(3));
                asistente.setText(fila.getString(4));
                sql.close();
            } else {
                Toast.makeText(this,"No existe el registro", Toast.LENGTH_SHORT).show();
                sql.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el titulo del registro", Toast.LENGTH_SHORT).show();
        }
    }




    public void guardar(View view){
        APPSQLiteOpenHelper con= new APPSQLiteOpenHelper(this,"notas",null,1);
        SQLiteDatabase sql = con.getWritableDatabase();

        String tit= titulo.getText().toString();
        String ubic= ubicacion.getText().toString();
        String init= inicio.getText().toString();
        String end= fin.getText().toString();
        String assis= asistente.getText().toString();


        if(!tit.isEmpty() && !ubic.isEmpty() && !init.isEmpty() && !end.isEmpty() && !assis.isEmpty()){
            ContentValues registro= new ContentValues();

            registro.put("titulo",tit);
            registro.put("ubicacion",ubic);
            registro.put("fecha_inicio",init);
            registro.put("fecha_final",end);
            registro.put("asistentes",assis);

            sql.insert("notas",null,registro);

            sql.close();
            titulo.setText("");
            ubicacion.setText("");
            inicio.setText("");
            fin.setText("");
            asistente.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    public void eliminar(View view){
        APPSQLiteOpenHelper con= new APPSQLiteOpenHelper(this,"notas",null,1);
        SQLiteDatabase sql = con.getWritableDatabase();

        String cod= codigo.getText().toString();


        if(!cod.isEmpty()){
            int cantidad = sql.delete("notas","_id=" + cod,null);
            sql.close();
            titulo.setText("");
            ubicacion.setText("");
            inicio.setText("");
            fin.setText("");
            asistente.setText("");

            if(cantidad >0){
                Toast.makeText(this, "Registro eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El registro no existe", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Debes llenar el campo requerido", Toast.LENGTH_SHORT).show();

        }

    }


    public void Modificar(View view){
        APPSQLiteOpenHelper con = new APPSQLiteOpenHelper(this, "notas", null, 1);
        SQLiteDatabase sql = con.getWritableDatabase();


        String cod= codigo.getText().toString();
        String tit = titulo.getText().toString();
        String ubic = ubicacion.getText().toString();
        String init = inicio.getText().toString();
        String end = fin.getText().toString();
        String assis = asistente.getText().toString();

        if(!cod.isEmpty() && !tit.isEmpty() && !ubic.isEmpty() && !init.isEmpty() && !end.isEmpty() && !assis.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("titulo", tit);
            registro.put("ubicacion", ubic);
            registro.put("fecha_inicio", init);
            registro.put("fecha_final", end);
            registro.put("asistentes", assis);

            int cantidad = sql.update("notas", registro, "_id=" + cod, null);

            sql.close();
            titulo.setText("");
            ubicacion.setText("");
            inicio.setText("");
            fin.setText("");
            asistente.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Registro modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El Registro no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }








}


