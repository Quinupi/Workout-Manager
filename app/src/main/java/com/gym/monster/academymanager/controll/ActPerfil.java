package com.gym.monster.academymanager.controll;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gym.monster.academymanager.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victo on 04/09/2017.
 */

public class ActPerfil extends AppCompatActivity implements ValueEventListener, View.OnClickListener {
    private TextView textViewIMCValor;
    private Button buttonSalvarAlteracaoPeso;
    private EditText editTextAlteraPeso;
    private FirebaseDatabase DBroot;
    private DatabaseReference DBref;
    private FirebaseAuth FBAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_perfil);

        FBAuth = FirebaseAuth.getInstance();
        DBroot = FirebaseDatabase.getInstance();

        textViewIMCValor = (TextView) findViewById(R.id.TextViewIMCValor);
        buttonSalvarAlteracaoPeso = (Button) findViewById(R.id.buttonSalvarAlteracaoPeso);
        editTextAlteraPeso = (EditText) findViewById(R.id.EditTextAlterarPeso);

        String email = FBAuth.getCurrentUser().getEmail().toString();
        email = email.replace(".", "");
        email = email.replace("#", "");
        email = email.replace("$", "");
        email = email.replace("[", "");
        email = email.replace("]", "");
        email = email.replace("@", "");

        DBref = DBroot.getReference().child("PerfilUsuario").child(email);

        DBref.addValueEventListener(this);

        buttonSalvarAlteracaoPeso.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.action_bar_act_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder alertDialogInfoIMC = new AlertDialog.Builder(this);
        alertDialogInfoIMC.setMessage("IMC               Classificação\n" +
                                      "< 16               Magreza grave\n" +
                                      "16 a < 17      Magreza moderada\n" +
                                      "17 a < 18,5    Magreza leve\n" +
                                      "18,5 a < 25    Saudável\n" +
                                      "25 a < 30       Sobrepeso\n" +
                                      "30 a < 35       Obesidade Grau I\n" +
                                      "35 a < 40       Obesidade Grau II \n" +
                                      "> 40               Obesidade Grau III ");
        alertDialogInfoIMC.setNeutralButton("Ok", null);
        alertDialogInfoIMC.show();
        return true;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String alturaUsuarioString = dataSnapshot.child("AlturaUsuario").getValue().toString();
        String pesoUsuarioString = dataSnapshot.child("PesoUsuario").getValue().toString();

        float alturaUsuario = Float.parseFloat(alturaUsuarioString);
        float pesoUsuario = Float.parseFloat(pesoUsuarioString);

        float imcUsuario = pesoUsuario / (alturaUsuario * alturaUsuario);

        textViewIMCValor.setText(Float.toString(imcUsuario));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {
        Map<String, Object> updateMap = new HashMap<String, Object>();

        updateMap.put("PesoUsuario", editTextAlteraPeso.getText().toString());

        DBref.updateChildren(updateMap);
    }
}
