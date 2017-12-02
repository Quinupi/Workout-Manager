package com.gym.monster.academymanager.controll;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoCategoriaExercicio;
import com.gym.monster.academymanager.data.DaoExercicio;
import com.gym.monster.academymanager.model.CategoriaExercicio;
import com.gym.monster.academymanager.model.Exercicio;

public class ActExercicio extends AppCompatActivity implements View.OnClickListener {
    private Exercicio exercicio = new Exercicio();
    private DaoExercicio daoExercicio;
    private DaoCategoriaExercicio daoCategoria;
    private Button btnSalvar;
    private EditText edtPeso, edtRepeticoes, edtSeries;
    private TextView txtNome, txtCategoria, txtDescricao;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_exercicio);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtCategoria = (TextView) findViewById(R.id.txtCategoria);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        edtPeso =  (EditText) findViewById(R.id.edtPeso);
        edtRepeticoes = (EditText) findViewById(R.id.edtRepeticoes);
        edtSeries = (EditText) findViewById(R.id.edtSeries);

        daoCategoria = new DaoCategoriaExercicio(ActExercicio.this);
        daoExercicio = new DaoExercicio(ActExercicio.this);

        bundle = getIntent().getExtras();
        exercicio = daoExercicio.pesquisaExercicio(bundle.getInt("ID_EXERCICIO"), bundle.getInt("ID_CATEGORIA"));
        CategoriaExercicio categoria = daoCategoria.buscarCategoriaExercicio(exercicio.getIdCategoria());

        txtNome.setText(exercicio.getNome());
        txtCategoria.setText(String.valueOf(categoria.getNomeCategoria()));
        txtDescricao.setText(exercicio.getDescricao());
        edtPeso.setText(String.valueOf(exercicio.getPeso()));
        edtRepeticoes.setText(String.valueOf(exercicio.getQtdRepeticoes()));
        edtSeries.setText(String.valueOf(exercicio.getQtdSeries()));

        btnSalvar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        double peso = Double.parseDouble(edtPeso.getText().toString());
        int repeticoes = Integer.parseInt(edtRepeticoes.getText().toString());
        int qtdSeries = Integer.parseInt(edtSeries.getText().toString());

        daoExercicio.alteraExercicio(bundle.getInt("ID_EXERCICIO"), peso, repeticoes, qtdSeries);

        Toast.makeText(this, "Exercicio alterado com sucesso", Toast.LENGTH_SHORT).show();

    }
}
