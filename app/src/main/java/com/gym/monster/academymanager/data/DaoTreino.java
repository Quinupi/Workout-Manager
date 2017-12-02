package com.gym.monster.academymanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gym.monster.academymanager.model.Exercicio;
import com.gym.monster.academymanager.model.Treino;
import com.gym.monster.academymanager.model.Usuario;

import java.util.ArrayList;

/**
 * Created by victor on 15/02/17.
 */

public class DaoTreino {
    private ArrayList<Treino> arrayTreino;
    private ArrayList<Exercicio> arrayExercicio;
    private AcademyManagerDB db;
    private Cursor cursor;

    public DaoTreino(Context context){
        db = new AcademyManagerDB(context);
    }



    public ArrayList<Treino> pesquisaTreinos(){
        arrayTreino = new ArrayList<Treino>();

        String[] colunas = new String[]{"ID_TREINO", "NOME"};

        cursor = db.getDb().query("TREINO", colunas, null, null, null, null, "ID_TREINO ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Treino treino = new Treino(cursor.getString(1), cursor.getLong(0));
                arrayTreino.add(treino);
            }while (cursor.moveToNext());

        }


        return arrayTreino;
    }

    public Treino pesquisaTreino(long idTreino){
        Treino treino = new Treino();;

        String[] colunas = new String[]{"NOME"};

        cursor = db.getDb().query("TREINO", colunas, "ID_TREINO = ?", new String[]{""+idTreino}, null, null, "ID_TREINO ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            treino.setNomeTreino(cursor.getString(0));


        }


        return treino;
    }

    public String[] pesquisaTipoTreino(){
        String[] arrayTipoTreino;

        String[] colunas = new String[]{"NOME_TIPO_TREINO"};

        cursor = db.getDb().query("REL_TIPO_TREINO", colunas, null, null, null, null, "ID_TIPO_TREINO ASC");

        arrayTipoTreino = new String[cursor.getCount()];

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            int i = 0;

            do{
                arrayTipoTreino[i] = cursor.getString(0);
                i++;
            }while (cursor.moveToNext());

        }


        return arrayTipoTreino;
    }

    public ArrayList<Exercicio> buscarExerciciosPorTreino(long idTreino){
        arrayExercicio = new ArrayList<Exercicio>();

        final String QUERY = "SELECT EXE.* FROM EXERCICIO EXE " +
                                                        "INNER JOIN " +
                                                "REL_TREINO_EXERCICIO RTE " +
                                                "ON EXE.ID_EXERCICIO = RTE.ID_EXERCICIO " +
                                                "WHERE RTE.ID_TREINO = ?";

        cursor = db.getDb().rawQuery(QUERY, new String[]{""+idTreino});

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Exercicio exercicio = new Exercicio(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), "", 0, 0, 0.0);
                arrayExercicio.add(exercicio);
            }while (cursor.moveToNext());

        }

        return arrayExercicio;
    }

    public boolean deletarExercicioTreino(long idTreino, int idExercicio){
        try {
            db.getDb().delete("REL_TREINO_EXERCICIO", "ID_TREINO = ? AND ID_EXERCICIO = ?", new String[]{"" + idTreino, "" + idExercicio});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deletarTodosExercicioTreino(long idTreino){
        try {
            db.getDb().delete("REL_TREINO_EXERCICIO", "ID_TREINO = ?", new String[]{"" + idTreino});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public long adicionaTreino(Treino t){
        Treino treino = t;

        ContentValues content = new ContentValues();

        content.put("NOME", treino.getNomeTreino());

        long idTreinoInserido = db.getDb().insert("TREINO", null, content);

        return idTreinoInserido;
    }

    public void adicionaExercicioNoTreino(long idTreino, ArrayList<Exercicio> exercicios){
        for (Exercicio e: exercicios) {
            ContentValues content = new ContentValues();

            content.put("ID_EXERCICIO", e.getIdExercicio());
            content.put("ID_TREINO", idTreino);

            db.getDb().insert("REL_TREINO_EXERCICIO", null, content);
        }
    }

    public boolean deletaTreino(long idTreino){
        try {
            db.getDb().delete("TREINO", "ID_TREINO = ?", new String[]{"" + idTreino});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean alteraTreino(long idTreino, Treino treino, ArrayList<Exercicio> exercicios){
        ContentValues content;

        try {
            db.getDb().delete("REL_TREINO_EXERCICIO", "ID_TREINO = ?", new String[]{"" + idTreino});

            content = new ContentValues();

            content.put("NOME", treino.getNomeTreino());

            db.getDb().update("TREINO", content,"ID_TREINO = ?", new String[]{""+idTreino});

            for (Exercicio e: exercicios) {
                content = new ContentValues();

                content.put("ID_EXERCICIO", e.getIdExercicio());
                content.put("ID_TREINO", idTreino);

                db.getDb().insert("REL_TREINO_EXERCICIO", null, content);
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }

}
