package com.example.loginapp;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


    public class APPSQLiteOpenHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION=1;
        public static final String DATABASE_NAME="project.db";





        public APPSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE notas(codigo integer primary key, titulo varchar(50) unique not null ," +
                    "ubicacion varchar(50) not null,fecha_inicio varchar(20) not null, fecha_final varchar(20) not null, asistentes int not null)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            try{
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME + "");
            }
            catch(SQLException e){
                //exepciones
            }
        }


    }


