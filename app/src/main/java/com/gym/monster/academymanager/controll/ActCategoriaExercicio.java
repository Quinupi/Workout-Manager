package com.gym.monster.academymanager.controll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoCategoriaExercicio;
import com.gym.monster.academymanager.model.CategoriaExercicio;
import com.gym.monster.academymanager.model.Exercicio;
import com.gym.monster.academymanager.model.modelAdapters.CategoriaExercicioAdapter;

import java.util.ArrayList;

public class ActCategoriaExercicio extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lstCategoriaExercio;
    private CategoriaExercicioAdapter adpCatExe;
    private CategoriaExercicio catExe;
    private Intent intent;
    private Bundle bundle;
    private DaoCategoriaExercicio daoCategoriaExercicio;
    private ArrayList<Exercicio> arrayExercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_categoria_exercicio);

        lstCategoriaExercio = (ListView) findViewById(R.id.lstCategoriaExercicio);
        bundle = getIntent().getExtras();

        daoCategoriaExercicio = new DaoCategoriaExercicio(ActCategoriaExercicio.this);

        arrayExercicios = (ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY");

        adpCatExe = new CategoriaExercicioAdapter(ActCategoriaExercicio.this, daoCategoriaExercicio.buscarCategoriasExercicio());

        lstCategoriaExercio.setAdapter(adpCatExe);
        lstCategoriaExercio.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        catExe = (CategoriaExercicio) lstCategoriaExercio.getItemAtPosition(position);

        intent = new Intent(ActCategoriaExercicio.this, ActListaExercicios.class);
        intent.putExtra("FLAG", bundle.getInt("FLAG"));
        intent.putExtra("ARRAY", arrayExercicios);
        intent.putExtra("NOVO_TREINO", bundle.getInt("NOVO_TREINO"));
        intent.putExtra("ID_CATEGORIA", catExe.getIdCategoria());
        intent.putExtra("ID_TREINO", bundle.getLong("ID_TREINO"));


        ActCategoriaExercicio.this.startActivity(intent);

    }
}
