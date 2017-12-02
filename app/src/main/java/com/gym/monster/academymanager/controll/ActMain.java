package com.gym.monster.academymanager.controll;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.gym.monster.academymanager.R;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnTreino, btnDieta, btnPerfil, btnExercicios, btnAMChat, btnConfig;
    private ArrayList<ImageButton> btnlist;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mAuth = FirebaseAuth.getInstance();

        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().getInt("LOGIN_SUCCESS") == 1)
                Toast.makeText(this, "Bem-vindo "+mAuth.getCurrentUser().getDisplayName().toString(), Toast.LENGTH_SHORT).show();
        }

        btnDieta = (ImageButton) findViewById(R.id.btnDieta);
        btnTreino = (ImageButton) findViewById(R.id.btnTreino);
        btnPerfil = (ImageButton) findViewById(R.id.btnPerfil);
        btnExercicios = (ImageButton) findViewById(R.id.btnExercicios);
        btnAMChat = (ImageButton) findViewById(R.id.btnAMChat);
        btnConfig = (ImageButton) findViewById(R.id.btnConfig);

        btnlist = new ArrayList<ImageButton>();
        btnlist.add(btnDieta);
        btnlist.add(btnTreino);
        btnlist.add(btnPerfil);
        btnlist.add(btnExercicios);
        btnlist.add(btnAMChat);
        btnlist.add(btnConfig);

        for(ImageButton btn:btnlist) {
            btn.setOnClickListener(this);
        }
    }

    private void iniciarActivityCadastroUsuario() {
        Intent intent = new Intent(getApplicationContext(), ActCadastroUsuario.class);
        getApplicationContext().startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent it;
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);

        switch (id){
            case R.id.btnTreino :
                it = new Intent(getApplicationContext(), ActTreino.class);
                getApplicationContext().startActivity(it);
                break;
            case R.id.btnDieta :
                dlg.setMessage(":::::::::Área não criada:::::::::");
                dlg.setNeutralButton("OK", null);
                dlg.show();
                break;
            case R.id.btnPerfil :
                it = new Intent(getApplicationContext(), ActPerfil.class);
                getApplicationContext().startActivity(it);
                break;
            case R.id.btnExercicios :
                it = new Intent(getApplicationContext(), ActCategoriaExercicio.class);
                it.putExtra("FLAG", 0);
                it.putExtra("ID_TREINO", 0);
                getApplicationContext().startActivity(it);
                break;
            case R.id.btnHelp :
                dlg.setMessage(":::::::::Área não criada:::::::::");
                dlg.setNeutralButton("OK", null);
                dlg.show();
                break;

            case R.id.btnAMChat :
                it = new Intent(getApplicationContext(), ActAdicionaChat.class);
                getApplicationContext().startActivity(it);
                break;

            case R.id.btnConfig :
                it = new Intent(getApplicationContext(), ActConfiguracao.class);
                getApplicationContext().startActivity(it);
                break;
        }
    }
}
