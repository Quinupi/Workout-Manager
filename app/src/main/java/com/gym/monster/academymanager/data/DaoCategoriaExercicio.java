package com.gym.monster.academymanager.data;

import android.content.Context;
import android.database.Cursor;

import com.gym.monster.academymanager.model.CategoriaExercicio;

import java.util.ArrayList;

/**
 * Created by victor on 23/02/17.
 */

public class DaoCategoriaExercicio {
    private AcademyManagerDB db;
    private ArrayList<CategoriaExercicio> categorias;
    private Cursor cursor;

    public DaoCategoriaExercicio(Context c){
        db = new AcademyManagerDB(c);

    }

    public ArrayList<CategoriaExercicio> buscarCategoriasExercicio(){
        categorias = new ArrayList<CategoriaExercicio>();
        String[] colunas = new String[]{"ID_CATEGORIA", "NOME"};

        cursor = db.getDb().query("CATEGORIA_EXERCICIO", colunas, null, null, null, null, "ID_CATEGORIA ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                CategoriaExercicio categoria = new CategoriaExercicio(cursor.getInt(0), cursor.getString(1));
                categorias.add(categoria);
            }while (cursor.moveToNext());

        }

        return categorias;
    }

    public CategoriaExercicio buscarCategoriaExercicio(int idCategoria){
        CategoriaExercicio categoria;

        String[] colunas = new String[]{"ID_CATEGORIA", "NOME"};

        Cursor cur = db.getDb().query("CATEGORIA_EXERCICIO", colunas, "ID_CATEGORIA = ?", new String[]{""+idCategoria}, null, null, "ID_CATEGORIA ASC");

        cur.moveToFirst();

        categoria = new CategoriaExercicio(cur.getInt(0), cur.getString(1));


        return categoria;
    }
}
