package com.gym.monster.academymanager.controll;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.gym.monster.academymanager.R;

/**
 * Created by victo on 15/08/2017.
 */

public class ActConfiguracao extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_configuracao);

        btnLogout = (Button) findViewById(R.id.btnFBLogout);

      /*  if(AccessToken.getCurrentAccessToken() == null){
            btnLogout.setClickable(false);
        }*/

        btnLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent(getApplicationContext(), ActLogin.class);
            LoginManager.getInstance().logOut();
            Snackbar.make(findViewById(R.id.act_configuracao), "LogOut feito com sucesso.", Snackbar.LENGTH_SHORT);
            getApplicationContext().startActivity(intent);

        }catch (Exception ex){
            Snackbar.make(findViewById(R.id.act_configuracao), "Ocorreu um erro ao tentar se desconectar" + ex.getMessage(), Snackbar.LENGTH_SHORT);
        }
    }
}
