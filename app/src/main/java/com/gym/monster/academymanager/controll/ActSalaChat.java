package com.gym.monster.academymanager.controll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gym.monster.academymanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by victo on 06/08/2017.
 */

public class ActSalaChat extends AppCompatActivity implements View.OnClickListener, ChildEventListener {
    private EditText txtMensagem;
    private ImageButton btnEnviarMensagem;
    private ListView lstMensagemChat;
    private TextView txtViewNomeDaSala;
    private String nomeUsuario, nomeDaSala, chaveTemporaria, mensagemChat;
    private DatabaseReference DBroot;
    private ArrayAdapter<String> adpMensagem;
    private ArrayList<String> alMensagem  = new ArrayList<>();
    private FirebaseAuth FBAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sala_chat);

        FBAuth = FirebaseAuth.getInstance();

        txtMensagem = (EditText) findViewById(R.id.txtMensagem);
        btnEnviarMensagem = (ImageButton) findViewById(R.id.btnEnviarMensagem);
        lstMensagemChat = (ListView) findViewById(R.id.lstMensagemChat);
        txtViewNomeDaSala = (TextView) findViewById(R.id.txtViewNomeDaSala);

        adpMensagem = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alMensagem);
        lstMensagemChat.setAdapter(adpMensagem);

        nomeUsuario = FBAuth.getCurrentUser().getDisplayName().toString();
        nomeDaSala = getIntent().getExtras().getString("nomeDaSala");

        txtViewNomeDaSala.setText("Sala - " + nomeDaSala);

        DBroot = FirebaseDatabase.getInstance().getReference().child("SalasChat");

        DBroot = DBroot.child(nomeDaSala);

        btnEnviarMensagem.setOnClickListener(this);

        DBroot.addChildEventListener(this);
    }

    @Override
    public void onClick(View v) {
        Map<String, Object> mapSala = new HashMap<String, Object>();
        chaveTemporaria = DBroot.push().getKey();
        DBroot.updateChildren(mapSala);

        DatabaseReference mensagemRoot = DBroot.child(chaveTemporaria);
        Map<String, Object> mapMensagem = new HashMap<String, Object>();
        mapMensagem.put("nomeUsuario", nomeUsuario);
        mapMensagem.put("txtMensagem", txtMensagem.getText().toString());

        mensagemRoot.updateChildren(mapMensagem);

        txtMensagem.getText().clear();
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
       adicionarMensagemChat(dataSnapshot);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }


    private void adicionarMensagemChat(DataSnapshot dataSnapshot) {
        Set<String> set = new HashSet<String>();
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()){
            set.add((String)((DataSnapshot) i.next()).getValue() + " : "+ (String)((DataSnapshot) i.next()).getValue());
        }

        alMensagem.addAll(set);

        adpMensagem.notifyDataSetChanged();

    }

}
