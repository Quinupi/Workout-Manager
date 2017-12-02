package com.gym.monster.academymanager.controll;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoExercicio;
import com.gym.monster.academymanager.model.Exercicio;

import java.util.ArrayList;

public class ActAdicionaExercicio extends AppCompatActivity implements View.OnClickListener {
    private Exercicio exercicio;
    private DaoExercicio dao;
    private ArrayList<Exercicio> arrayExercicios;
    private Button btnAdicionar;
    private EditText edtPeso, edtRepeticoes, edtSeries;
    private TextView txtNome, txtCategoria, txtDescricao;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_adiciona_exercicio);

        btnAdicionar = (Button) findViewById(R.id.btnAdicionarExercicioTreino);
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtCategoria = (TextView) findViewById(R.id.txtCategoria);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        edtPeso =  (EditText) findViewById(R.id.edtPeso);
        edtRepeticoes = (EditText) findViewById(R.id.edtRepeticoes);
        edtSeries = (EditText) findViewById(R.id.edtSeries);

        bundle = getIntent().getExtras();



        arrayExercicios = (ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY");

        dao = new DaoExercicio(ActAdicionaExercicio.this);

        exercicio = dao.pesquisaExercicio(bundle.getInt("ID_EXERCICIO"), bundle.getInt("ID_CATEGORIA"));

        txtNome.setText(exercicio.getNome());
        txtCategoria.setText(String.valueOf(exercicio.getIdCategoria()));
        txtDescricao.setText(exercicio.getDescricao());
        edtPeso.setText(String.valueOf(exercicio.getPeso()));
        edtRepeticoes.setText(String.valueOf(exercicio.getQtdRepeticoes()));
        edtSeries.setText(String.valueOf(exercicio.getQtdSeries()));

        btnAdicionar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        double peso = Double.parseDouble(edtPeso.getText().toString());
        int repeticoes = Integer.parseInt(edtRepeticoes.getText().toString());
        int qtdSeries = Integer.parseInt(edtSeries.getText().toString());


        dao.alteraExercicio(bundle.getInt("ID_EXERCICIO"), peso, repeticoes, qtdSeries);

        AlertDialog.Builder dialog = new AlertDialog.Builder(ActAdicionaExercicio.this);
        dialog.setMessage("Exercício adicionado ao treino.");
        dialog.setTitle("Sucesso.");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ActAdicionaExercicio.this, ActExibeTreino.class);

                intent.putExtra("NOVO_TREINO", bundle.getInt("NOVO_TREINO"));

                arrayExercicios.add(exercicio);

                intent.putExtra("ID_TREINO", bundle.getLong("ID_TREINO"));
                intent.putExtra("ARRAY", arrayExercicios);

                ActAdicionaExercicio.this.startActivity(intent);
            }
        });

        dialog.show();

    }

}
