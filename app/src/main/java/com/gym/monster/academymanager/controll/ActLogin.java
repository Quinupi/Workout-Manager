package com.gym.monster.academymanager.controll;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.gym.monster.academymanager.R;

/**
 * Created by victo on 10/08/2017.
 */

public class ActLogin extends AppCompatActivity  {

    private LoginButton btnLogin;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        mAuth = FirebaseAuth.getInstance();

        intent = new Intent(getApplicationContext(), ActCadastroUsuario.class);

        callbackManager = CallbackManager.Factory.create();

        btnLogin = (LoginButton) findViewById(R.id.btnFbLogin);
        btnLogin.setReadPermissions("email", "public_profile");

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                iniciarActivityPrincipal();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            intent.putExtra("LOGIN_SUCCESS", 1);
                        } else {
                            Log.w("signInWithCredential:failure", task.getException());
                            Toast.makeText(ActLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


    private void iniciarActivityPrincipal() {
        getApplicationContext().startActivity(intent);
    }
}
