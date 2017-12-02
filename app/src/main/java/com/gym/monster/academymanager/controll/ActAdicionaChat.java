package com.gym.monster.academymanager.controll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class ActAdicionaChat extends AppCompatActivity {
    private ImageButton btnAdicionarSala;
    private ListView lstSalasChat;
    private EditText txtNomeDaSala;
    private ArrayAdapter<String> adpListaSalas;
    private ArrayList<String> alListaSalas = new ArrayList<>();
    private String name;
    private Context context;
    private DatabaseReference DBroot = FirebaseDatabase.getInstance().getReference().getRoot().child("SalasChat");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_adiciona_chat);
        context = this;

        btnAdicionarSala = (ImageButton) findViewById(R.id.btnAdicionarSala);
        lstSalasChat = (ListView) findViewById(R.id.lstSalasChat);
        txtNomeDaSala = (EditText) findViewById(R.id.txtNomeDaSala);

        adpListaSalas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alListaSalas);

        lstSalasChat.setAdapter(adpListaSalas);

        btnAdicionarSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(txtNomeDaSala.getText().toString(), "");
                DBroot.updateChildren(map);
            }
        });

        requisicaoNomeDeUsuario();
    }

    private void requisicaoNomeDeUsuario() {
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Entre com o nome : ");

        final EditText campoDeEntrada = new EditText(this);

        builder.setView(campoDeEntrada);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = campoDeEntrada.getText().toString();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent it = new Intent(context, ActAdicionaChat.class);
                context.startActivity(it);
            }
        });
*/
        DBroot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot) i.next()).getKey());
                }

                alListaSalas.clear();
                alListaSalas.addAll(set);

                adpListaSalas.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lstSalasChat.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent it = new Intent(context, ActSalaChat.class);
                it.putExtra("nomeDaSala", ((TextView)view).getText().toString());
                context.startActivity(it);

            }
        });

      //  builder.show();


    }
}
