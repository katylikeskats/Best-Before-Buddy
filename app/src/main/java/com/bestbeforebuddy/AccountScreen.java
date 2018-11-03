package com.bestbeforebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AccountScreen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_screen);

        //buttons

    }


    @Override
    public void onClick(View v){
        int i = v.getId();
    }

}
