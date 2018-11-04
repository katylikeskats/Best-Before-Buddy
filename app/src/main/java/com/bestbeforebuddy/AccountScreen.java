package com.bestbeforebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AccountScreen extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_screen);

        //buttons
        findViewById(R.id.notificationButton).setOnClickListener(this);
        findViewById(R.id.accountButton).setOnClickListener(this);
        findViewById(R.id.inventoryButton).setOnClickListener(this);

        TextView textView = findViewById(R.id.welcomeMessage);
        textView.setText("Hi test!");
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.notificationButton){
            Intent intent = new Intent(this, NotificationScreen.class);
            startActivity(intent);
        } else if (i==R.id.inventoryButton){
            Intent intent = new Intent(this, InventoryScreen.class);
            startActivity(intent);
        }
    }

}
