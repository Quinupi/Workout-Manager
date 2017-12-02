package com.gym.monster.academymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gym.monster.academymanager.model.Exercicio;

import java.util.ArrayList;

/**
 * Created by victor on 25/02/17.
 */

public class DaoExercicio {
    private static ArrayList<Exercicio> arrayExercicio;
    private AcademyManagerDB db;
    private Cursor cursor;

    public DaoExercicio(Context c){
        db = new AcademyManagerDB(c);
    }


    public ArrayList<Exercicio> buscarExerciciosPorCategoria(int idCategoria){
        arrayExercicio = new ArrayList<Exercicio>();

        String[] colunas = new String[]{"ID_EXERCICIO", "ID_CATEGORIA", "NOME"};

        cursor = db.getDb().query("EXERCICIO", colunas, "ID_CATEGORIA = ?", new String[]{""+idCategoria} , null, null, "ID_EXERCICIO ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Exercicio exercicio = new Exercicio(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), "", 0, 0, 0.0);
                arrayExercicio.add(exercicio);
            }while (cursor.moveToNext());

        }

        return arrayExercicio;
    }

    public void alteraExercicio(int idExercicio, double peso, int qtdRepeticoes, int qtdSeries){
        ContentValues content = new ContentValues();

        content.put("PESO", peso);
        content.put("QTD_SERIES", qtdSeries);
        content.put("QTD_REPETICOES", qtdRepeticoes);

        db.getDb().update("EXERCICIO", content, "ID_EXERCICIO = ?", new String[]{""+idExercicio});

    }

    public Exercicio pesquisaExercicio(int idExercicio, int idCategoria){
        Exercicio e;

        String[] colunas = new String[]{"ID_EXERCICIO", "ID_CATEGORIA", "NOME", "DESCRICAO", "QTD_SERIES", "QTD_REPETICOES", "PESO"};

        cursor = db.getDb().query("EXERCICIO", colunas, "ID_EXERCICIO = ? AND ID_CATEGORIA = ?", new String[]{""+idExercicio, ""+idCategoria} , null, null, null);

        cursor.moveToFirst();

        e = new Exercicio(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getDouble(6));

        return e;
    }
}
