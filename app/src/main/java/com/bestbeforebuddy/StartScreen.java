package com.bestbeforebuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        //buttons
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.signUpButton).setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.signUpButton){
            Intent intent = new Intent(this, SignUpScreen.class);
            startActivity(intent);
        } else if (i == R.id.loginButton) {
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }
    }
}
