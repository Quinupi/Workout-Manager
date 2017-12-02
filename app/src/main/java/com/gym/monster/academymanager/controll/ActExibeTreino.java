package com.gym.monster.academymanager.controll;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoTreino;
import com.gym.monster.academymanager.model.Exercicio;
import com.gym.monster.academymanager.model.Treino;
import com.gym.monster.academymanager.model.modelAdapters.ExercicioAdapter;

import java.util.ArrayList;

public class ActExibeTreino extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private Bundle bundle;
    private ArrayList<Exercicio> arrayExercicios;
    private ExercicioAdapter adpExercicio;
    private int p;
    private long idTreino;
    private Treino treino;
    private DaoTreino daoTreino;
    private ImageButton btnAdicionarExercicio;
    private Button btnSalvar, btnCancelar;
    private ListView lstExercicios;
    private EditText edtNomeTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_treino);

        btnAdicionarExercicio = (ImageButton) findViewById(R.id.btnAdicionarExercicio);
        btnSalvar = (Button) findViewById(R.id.btnSalvarTreino);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        lstExercicios = (ListView) findViewById(R.id.lstExercicios);
        edtNomeTreino = (EditText) findViewById(R.id.edtNomeTreino);

        daoTreino = new DaoTreino(ActExibeTreino.this);

        bundle = getIntent().getExtras();


        //Quando o treino existir na base aqui retornarão seus exercícios
        if(bundle.getInt("NOVO_TREINO") == 0) {
            treino = daoTreino.pesquisaTreino(bundle.getLong("ID_TREINO"));

            edtNomeTreino.setText(treino.getNomeTreino());

            arrayExercicios = new ArrayList<Exercicio>();

            if(daoTreino.buscarExerciciosPorTreino(bundle.getLong("ID_TREINO")) != null && (ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY")== null) {
                arrayExercicios = daoTreino.buscarExerciciosPorTreino(bundle.getLong("ID_TREINO"));
            }

            if((ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY")!= null){
                arrayExercicios = (ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY");
            }

            adpExercicio = new ExercicioAdapter(ActExibeTreino.this, arrayExercicios);
            lstExercicios.setAdapter(adpExercicio);

        }else{
            arrayExercicios = new ArrayList<Exercicio>();
            if((ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY")!= null){
                arrayExercicios.addAll((ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY"));
            }
            adpExercicio = new ExercicioAdapter(ActExibeTreino.this, arrayExercicios);
            lstExercicios.setAdapter(adpExercicio);
        }


        this.btnAdicionarExercicio.setOnClickListener(this);
        this.btnSalvar.setOnClickListener(this);
        this.btnCancelar.setOnClickListener(this);

        lstExercicios.setOnItemLongClickListener(this);
        lstExercicios.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        final Intent intent;
        AlertDialog.Builder dlg;

        switch (id){
            case R.id.btnAdicionarExercicio :
                intent = new Intent(ActExibeTreino.this, ActCategoriaExercicio.class);
                intent.putExtra("FLAG", 1);
                if (arrayExercicios == null){
                    arrayExercicios = new ArrayList<Exercicio>();
                    intent.putExtra("ARRAY", arrayExercicios);
                }else
                    intent.putExtra("ARRAY", arrayExercicios);

                intent.putExtra("NOVO_TREINO", bundle.getInt("NOVO_TREINO"));
                intent.putExtra("ID_TREINO", bundle.getLong("ID_TREINO"));

                ActExibeTreino.this.startActivity(intent);

                break;

            case R.id.btnSalvarTreino :
                if(edtNomeTreino.getText().toString().isEmpty()){
                    dlg = new AlertDialog.Builder(ActExibeTreino.this);
                    dlg.setMessage("Para que um treino possa ser salvo você precisa inserir um nome para ele.");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }else if(adpExercicio == null){
                    dlg = new AlertDialog.Builder(ActExibeTreino.this);
                    dlg.setMessage("Para que um treino possa ser salvo você precisa inserir ao menos um exercicio nele.");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }else{
                    if(bundle.getInt("NOVO_TREINO") == 0){
                        Treino t = new Treino();
                        t.setNomeTreino(edtNomeTreino.getText().toString());
                        daoTreino.alteraTreino(bundle.getLong("ID_TREINO"), t, arrayExercicios);
                    }else{
                        Treino t = new Treino();
                        t.setNomeTreino(edtNomeTreino.getText().toString());
                        idTreino = daoTreino.adicionaTreino(t);
                        t.setIdTreino(idTreino);
                        daoTreino.adicionaExercicioNoTreino(t.getIdTreino(), arrayExercicios);
                    }
                    intent = new Intent(ActExibeTreino.this, ActTreino.class);
                    dlg = new AlertDialog.Builder(ActExibeTreino.this);
                    dlg.setMessage("Treino salvo com sucesso.");
                    dlg.setNeutralButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActExibeTreino.this.startActivity(intent);
                        }
                    });
                    dlg.show();

                }
                break;

            case R.id.btnCancelar :
                intent = new Intent(ActExibeTreino.this, ActMain.class);
                ActExibeTreino.this.startActivity(intent);
                break;

        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        p = position;
        final Exercicio e = (Exercicio) lstExercicios.getItemAtPosition(p);
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActExibeTreino.this);
        dlg.setMessage("Você deseja deletar o exercício : " + e.getNome());
        dlg.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;

                if(bundle.getInt("NOVO_TREINO") == 0) {
                    intent = new Intent(ActExibeTreino.this, ActExibeTreino.class);
                    intent.putExtra("FLAG", 1);
                    intent.putExtra("NOVO_TREINO", 0);
                    intent.putExtra("ID_TREINO", bundle.getLong("ID_TREINO"));

                    if((ArrayList<Exercicio>)getIntent().getSerializableExtra("ARRAY")== null){
                        daoTreino.deletarExercicioTreino(bundle.getLong("ID_TREINO"), e.getIdExercicio());
                    }else{
                        arrayExercicios.remove(p);
                    }


                    ActExibeTreino.this.startActivity(intent);
                }else{
                    intent = new Intent(ActExibeTreino.this, ActExibeTreino.class);
                    arrayExercicios.remove(p);
                    intent.putExtra("FLAG", 1);
                    intent.putExtra("NOVO_TREINO", 1);
                    intent.putExtra("ARRAY", arrayExercicios);

                    ActExibeTreino.this.startActivity(intent);
                }
            }
        });
        dlg.setNegativeButton("Cancelar", null);
        dlg.show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Exercicio e = (Exercicio) lstExercicios.getItemAtPosition(position);

        Intent intent = new Intent(this, ActExercicio.class);
        intent.putExtra("ID_EXERCICIO", e.getIdExercicio());
        intent.putExtra("ID_CATEGORIA", e.getIdCategoria());
        getApplicationContext().startActivity(intent);
    }
}
