package com.gym.monster.academymanager.controll;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.data.DaoTreino;
import com.gym.monster.academymanager.data.DaoUsuario;
import com.gym.monster.academymanager.model.GeneroUsuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by victo on 16/08/2017.
 */

public class ActCadastroUsuario extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    private EditText EditTextNomeUsuario, EditTextPesoUsuario, EditTextAlturaUsuario;
    private Spinner SpinnerGeneroUsuario, SpinnerTipoTreinamentoUsuario, SpinnerIdadeUsuario;
    private ArrayList<String> ArrayGenero, ArrayTipoTreino, ArrayIdade;
    private ArrayAdapter<String> ArrayAdapterGenero, ArrayAdapterTipoTreino, ArrayAdapterÌdade;
    private Button ButtonSalvarPerfilUsuario;
    private String email;
    private FirebaseAuth mAuth;
    private FirebaseDatabase DBroot;
    private DatabaseReference DBref;
    private DaoUsuario daoUsuario;
    private DaoTreino daoTreino;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_usuario);

        mAuth = FirebaseAuth.getInstance();
        DBroot = FirebaseDatabase.getInstance();

        email = mAuth.getCurrentUser().getEmail().toString();
        email = email.replace(".", "");
        email = email.replace("#", "");
        email = email.replace("$", "");
        email = email.replace("[", "");
        email = email.replace("]", "");
        email = email.replace("@", "");

        DBref = DBroot.getReference().child("PerfilUsuario").child(email);

        daoUsuario = new DaoUsuario(getApplicationContext());
        daoTreino = new DaoTreino(getApplicationContext());

        ArrayGenero = new ArrayList<String>();
        ArrayTipoTreino = new ArrayList<String>();
        ArrayIdade = new ArrayList<String>();

        for(int i = 1; i <= 100; i++){
            ArrayIdade.add(Integer.toString(i));
        }

        for(int i = 0; i< daoTreino.pesquisaTipoTreino().length; i++){
            ArrayTipoTreino.add(daoTreino.pesquisaTipoTreino()[i]);
        }

        for(GeneroUsuario g : daoUsuario.pesquisaGenero()){
            ArrayGenero.add(g.getNomeGenero());
        }

        ArrayAdapterÌdade = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ArrayIdade);
        ArrayAdapterTipoTreino = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ArrayTipoTreino);
        ArrayAdapterGenero = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ArrayGenero);

        EditTextNomeUsuario = (EditText) findViewById(R.id.EditTextNomeUsuario);
        EditTextPesoUsuario = (EditText) findViewById(R.id.EditTextPesoUsuario);
        EditTextAlturaUsuario = (EditText) findViewById(R.id.EditTextAlturaUsuario);
        SpinnerIdadeUsuario = (Spinner) findViewById(R.id.SpinnerIdadeUsuario);
        SpinnerGeneroUsuario = (Spinner) findViewById(R.id.SpinnerGeneroUsuario);
        SpinnerTipoTreinamentoUsuario = (Spinner) findViewById(R.id.SpinnerTipoTreinamentoUsuario);
        ButtonSalvarPerfilUsuario = (Button) findViewById(R.id.ButtonSalvarPerfilUsuario);

        SpinnerIdadeUsuario.setAdapter(ArrayAdapterÌdade);
        SpinnerTipoTreinamentoUsuario.setAdapter(ArrayAdapterTipoTreino);
        SpinnerGeneroUsuario.setAdapter(ArrayAdapterGenero);

        EditTextNomeUsuario.setText(mAuth.getCurrentUser().getDisplayName());

        ButtonSalvarPerfilUsuario.setOnClickListener(this);
        DBref.addValueEventListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            Map<String, Object> mapUsuario = new HashMap<String, Object>();

            mapUsuario.put("NomeUsuario", EditTextNomeUsuario.getText().toString());
            mapUsuario.put("IdadeUsuario", SpinnerIdadeUsuario.getSelectedItemPosition() + 1);
            mapUsuario.put("PesoUsuario", EditTextPesoUsuario.getText().toString());
            mapUsuario.put("GeneroUsuario", SpinnerGeneroUsuario.getSelectedItemPosition());
            mapUsuario.put("AlturaUsuario", EditTextAlturaUsuario.getText().toString());
            mapUsuario.put("TipoTreinoUsuario", SpinnerTipoTreinamentoUsuario.getSelectedItemPosition());

            DBref.updateChildren(mapUsuario);

            Intent intent = new Intent(ActCadastroUsuario.this, ActMain.class);
            intent.putExtra("LOGIN_SUCCESS", 1);
            getApplicationContext().startActivity(intent);

        }catch(Exception e){
            Toast.makeText(ActCadastroUsuario.this, "Erro ao gravar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){
            Intent intent = new Intent(this, ActMain.class);
            getApplicationContext().startActivity(intent);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
