package com.bestbeforebuddy;

import android.content.Intent;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{
    EditText user;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //buttons
        findViewById(R.id.loginButton).setOnClickListener(this);
        //edit text
        user = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    protected void signIn(String user, String password) throws Exception{
        String[] array = Utils.encrypt(password);
        ArrayList<String> out = new HttpLogInRequest().doInBackground(user, array[0], array[1]);
        if (out.get(0).equals("Success")){
            Intent intent = new Intent(this, AccountScreen.class);
            startActivity(intent);
            Utils.user = user;
            Utils.password = password;
            ActionHandler.init();
        }
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.loginButton) {
            try {
                signIn(user.getText().toString(), password.getText().toString());
            } catch (java.lang.Exception e){
                System.out.println("failed");
                e.printStackTrace();
            }
        }
    }
}
