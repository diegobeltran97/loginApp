package com.example.loginapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.loginapp.APPSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaContactos extends AppCompatActivity {

    private EditText titulo, ubicacion,inicio,fin,asistente,codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titulo= (EditText)findViewById(R.id.txtTitulo);
        ubicacion= (EditText)findViewById(R.id.txtUbicacion);
        inicio= (EditText)findViewById(R.id.txtInicio);
        fin= (EditText)findViewById(R.id.txtFin);
        asistente= (EditText)findViewById(R.id.txtAsist);
        codigo= (EditText)findViewById(R.id.txtcodigo);

        getAllData();
    }
    public ArrayList<HashMap<String, String>> getAllData(){
        java.util.ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        try {
            APPSQLiteOpenHelper con = new APPSQLiteOpenHelper(this,"notas",null,1);
            SQLiteDatabase sql = con.getWritableDatabase();

            Cursor fila = sql.rawQuery
            ("select titulo,ubicacion, fecha_inicio, fecha_final, asistentes from notas" , null);

            if (fila != null) {
                if (fila.moveToFirst()) {
                    do {
                        HashMap<String,String> notas = new HashMap<>();
                        notas.put("titulo",fila.getString(0));
                        notas.put("ubicacion",fila.getString(1));
                        notas.put("fecha_inicio",fila.getString(2));
                        notas.put("fecha_final",fila.getString(3));
                        notas.put("asistentes",fila.getString(4));
                        userList.add(notas);
//                        ListAdapter adapter = new SimpleAdapter(this, userList, R.layout.list_row,
//                                new String[]{"nombre","telefono","correo"},
//                                new int[]{R.id.nombre, R.id.telefono, R.id.correo});
//                        listContactos.setAdapter(adapter);
                    } while (fila.moveToNext());
                }
            }else {
                Toast.makeText(this,"No hay registros agregados", Toast.LENGTH_SHORT).show();
                sql.close();
            }
        } catch(Exception e) {
            Toast.makeText(this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
        }
        return userList;
    }
}
