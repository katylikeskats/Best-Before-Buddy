package com.bestbeforebuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        //buttons
        findViewById(R.id.signUpButton).setOnClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signUpButton) {
            //communicate with william's database to verify stuff and login
        }
    }
}
