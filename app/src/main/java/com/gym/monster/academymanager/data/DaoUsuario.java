package com.gym.monster.academymanager.data;

import android.content.Context;
import android.database.Cursor;

import com.gym.monster.academymanager.model.GeneroUsuario;

import java.util.ArrayList;

/**
 * Created by victo on 22/08/2017.
 */

public class DaoUsuario {
    private AcademyManagerDB db;
    private Cursor cursor;


    public DaoUsuario(Context context) {
        db = new AcademyManagerDB(context);
    }

    public ArrayList<GeneroUsuario> pesquisaGenero(){
        ArrayList arrayGenero = new ArrayList<>();

        String[] colunas = new String[]{"ID_GENERO", "GENERO"};

        cursor = db.getDb().query("GENERO_USUARIO", colunas, null, null, null, null, "ID_GENERO ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                GeneroUsuario genero = new GeneroUsuario(cursor.getInt(0), cursor.getString(1));
                arrayGenero.add(genero);
            }while (cursor.moveToNext());

        }


        return arrayGenero;
    }
}
