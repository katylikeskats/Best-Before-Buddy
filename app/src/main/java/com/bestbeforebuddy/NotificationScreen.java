package com.bestbeforebuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class NotificationScreen extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_screen);

        //buttons
        findViewById(R.id.notificationButton).setOnClickListener(this);
        findViewById(R.id.accountButton).setOnClickListener(this);
        findViewById(R.id.inventoryButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.accountButton){
            Intent intent = new Intent(this, AccountScreen.class);
            startActivity(intent);
        } else if (i==R.id.inventoryButton){
            Intent intent = new Intent(this, InventoryScreen.class);
            startActivity(intent);
        }
    }
}
