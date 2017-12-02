package com.gym.monster.academymanager.controll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoExercicio;
import com.gym.monster.academymanager.model.Exercicio;
import com.gym.monster.academymanager.model.modelAdapters.ExercicioAdapter;

import java.util.ArrayList;

public class ActListaExercicios extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lstExercicios;
    private Bundle bundle;
    private ExercicioAdapter adpExercicio;
    private Intent intent;
    private DaoExercicio daoExercicio;
    private ArrayList<Exercicio> arrayExercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_exercicios);

        lstExercicios = (ListView) findViewById(R.id.lstExercicios);

        bundle = getIntent().getExtras();

        daoExercicio = new DaoExercicio(ActListaExercicios.this);

        arrayExercicios = (ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY");

        adpExercicio = new ExercicioAdapter(ActListaExercicios.this, daoExercicio.buscarExerciciosPorCategoria(bundle.getInt("ID_CATEGORIA")));

        lstExercicios.setAdapter(adpExercicio);

        lstExercicios.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Exercicio e = adpExercicio.getItem(position);

        if(bundle.getInt("FLAG") == 1){
            intent = new Intent(ActListaExercicios.this, ActAdicionaExercicio.class);
            intent.putExtra("ID_EXERCICIO", e.getIdExercicio());
            intent.putExtra("ID_CATEGORIA", bundle.getInt("ID_CATEGORIA"));
            intent.putExtra("ARRAY", arrayExercicios);
            intent.putExtra("NOVO_TREINO", bundle.getInt("NOVO_TREINO"));
            intent.putExtra("ID_TREINO", bundle.getLong("ID_TREINO"));

            ActListaExercicios.this.startActivity(intent);
        }else{
            intent = new Intent(ActListaExercicios.this, ActExercicio.class);
            intent.putExtra("ID_EXERCICIO", e.getIdExercicio());
            intent.putExtra("ID_CATEGORIA", bundle.getInt("ID_CATEGORIA"));

            ActListaExercicios.this.startActivity(intent);
        }
    }
}
