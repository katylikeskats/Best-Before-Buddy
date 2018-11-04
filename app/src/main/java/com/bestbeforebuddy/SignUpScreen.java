package com.bestbeforebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener{
    EditText user;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        //buttons
        findViewById(R.id.signUpButton).setOnClickListener(this);
        //edit text
        user = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    protected void signUp(String user, String password) throws Exception{
        //String[] array = encrypt(password);
        ArrayList<String> out = new HttpSignUpRequest().doInBackground(user, password);
        if (out.get(0).equals("Success")){
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signUpButton) {
            try {
                signUp(user.getText().toString(), password.getText().toString());
            } catch (java.lang.Exception e){

            }
        }
    }
}
