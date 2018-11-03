package com.bestbeforebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //buttons
        findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    public String[] encrypt(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            byte[] IV = new byte[16];
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec skeySpec = new SecretKeySpec(hash, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            byte[] encrypted = cipher.doFinal((sdf.format(now)).getBytes());

            return new String[]{Base64.encodeBase64String(encrypted),Base64.encodeBase64String(IV)};
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    protected void signIn(String user, String password){
        HttpGetParamRequest getRequest = new HttpGetParamRequest();
        String valid = getRequest.doInBackground(user, password);
        if (valid.equals("True")){
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.loginButton) {
        //communicate with william's database to verify stuff and login
        }
    }
}
