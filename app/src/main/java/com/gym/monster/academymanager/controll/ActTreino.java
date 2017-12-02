package com.gym.monster.academymanager.controll;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoExercicio;
import com.gym.monster.academymanager.data.DaoTreino;
import com.gym.monster.academymanager.model.Exercicio;
import com.gym.monster.academymanager.model.Treino;
import com.gym.monster.academymanager.model.modelAdapters.TreinoAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActTreino extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    private ImageButton imgBtnAdicionar;
    private TreinoAdapter adpTreino;
    private ListView lstTreino;
    private DaoTreino daoTreino;
    private DaoExercicio daoExercicio;
    private ArrayList<Treino> arrayListTreino;
    private ArrayList<Exercicio> arrayListExercicio;
    private ArrayList<Exercicio> ArrayListExercicioTemp;
    private Boolean treinoAleatorioJaGerado;
    private String[] arrayTreinoDiario = new String[]{" ", "Treino diário peito", "Treino diário perna",
                                                    "Treino diário costas", "Treino diário biceps",
                                                    "Treino diário triceps", "Treino diário abdomen",
                                                    "Treino diário ombro", "Treino diário trapézio"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_treino);

        daoTreino = new DaoTreino(this);
        daoExercicio = new DaoExercicio(this);

        imgBtnAdicionar = (ImageButton) findViewById(R.id.imgBtnAdicionar);
        lstTreino = (ListView) findViewById(R.id.lstTreino);

        adpTreino = new TreinoAdapter(ActTreino.this, daoTreino.pesquisaTreinos());

        lstTreino.setAdapter(adpTreino);

        lstTreino.setClickable(true);
        lstTreino.setOnItemClickListener(this);
        lstTreino.setOnItemLongClickListener(this);

        imgBtnAdicionar.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.action_bar_act_treino, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        arrayListTreino = daoTreino.pesquisaTreinos();
        treinoAleatorioJaGerado = false;

        if(arrayListTreino.size() > 0) {
            for (Treino t : arrayListTreino) {
                if (t.getNomeTreino().equals(arrayTreinoDiario[0]) || t.getNomeTreino().equals(arrayTreinoDiario[1]) || t.getNomeTreino().equals(arrayTreinoDiario[2]) ||
                        t.getNomeTreino().equals(arrayTreinoDiario[3]) || t.getNomeTreino().equals(arrayTreinoDiario[4]) || t.getNomeTreino().equals(arrayTreinoDiario[5]) ||
                        t.getNomeTreino().equals(arrayTreinoDiario[6]) || t.getNomeTreino().equals(arrayTreinoDiario[7])) {
                    treinoAleatorioJaGerado = true;
                }
            }
            if (treinoAleatorioJaGerado == false) {
                geraTreino();
            }else{
                deletaTreinoGerado();
                geraTreino();
            }
        }else{
                geraTreino();
        }

        Intent intent = new Intent(this, ActTreino.class);
        this.startActivity(intent);

        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.imgBtnAdicionar :
                Intent it = new Intent(ActTreino.this, ActExibeTreino.class);
                it.putExtra("NOVO_TREINO", 1);
                it.putExtra("FLAG", 1);
                ActTreino.this.startActivity(it);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Treino t = (Treino) lstTreino.getItemAtPosition(position);
        Intent it = new Intent(ActTreino.this, ActExibeTreino.class);
        it.putExtra("ID_TREINO", t.getIdTreino());
        it.putExtra("NOVO_TREINO", 0);
        it.putExtra("FLAG", 1);

        ActTreino.this.startActivity(it);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final Treino treino = (Treino) lstTreino.getItemAtPosition(position);

        AlertDialog.Builder dlg = new AlertDialog.Builder(ActTreino.this);
        dlg.setMessage("Você deseja deletar o treino : " + daoTreino.pesquisaTreino(treino.getIdTreino()).getNomeTreino());
        dlg.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ActTreino.this, ActTreino.class);

                daoTreino.deletaTreino(treino.getIdTreino());

                ActTreino.this.startActivity(intent);
            }
        });
        dlg.setNegativeButton("Cancelar", null);
        dlg.show();

        return true;
    }

    public void geraTreino(){
        for (int i = 1; i < 9; i++) {
            arrayListExercicio = daoExercicio.buscarExerciciosPorCategoria(i);
            ArrayListExercicioTemp = new ArrayList<Exercicio>();

            List<Integer> listIdExercicio = new ArrayList<Integer>();
            for (int j = 0; j < arrayListExercicio.size(); j++) {
                listIdExercicio.add(j);
            }
            Collections.shuffle(listIdExercicio);

            Treino treino = new Treino(arrayTreinoDiario[i]);

            long idTreino = daoTreino.adicionaTreino(treino);

            if(i != 8){
                for (int j = 0; j < 5; j++){
                    ArrayListExercicioTemp.add(arrayListExercicio.get(listIdExercicio.get(j)));
                }
            }else {
                for (int j = 0; j < 4; j++){
                    ArrayListExercicioTemp.add(arrayListExercicio.get(listIdExercicio.get(j)));
                }
            }

            daoTreino.adicionaExercicioNoTreino(idTreino, ArrayListExercicioTemp);

        }
    }

    private void deletaTreinoGerado() {
        arrayListTreino = daoTreino.pesquisaTreinos();
        for(Treino t : arrayListTreino){
            for(int i = 1; i < 9; i++) {
                if (t.getNomeTreino().equals(arrayTreinoDiario[i])){
                    daoTreino.deletaTreino(t.getIdTreino());
                    daoTreino.deletarTodosExercicioTreino(t.getIdTreino());
                }
            }
        }
    }

}
