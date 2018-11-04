package com.bestbeforebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InventoryScreen extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_screen);

        //buttons
        findViewById(R.id.notificationButton).setOnClickListener(this);
        findViewById(R.id.accountButton).setOnClickListener(this);
        findViewById(R.id.inventoryButton).setOnClickListener(this);
        findViewById(R.id.addButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.accountButton){
            Intent intent = new Intent(this, AccountScreen.class);
            startActivity(intent);
        } else if (i==R.id.notificationButton){
            Intent intent = new Intent(this, NotificationScreen.class);
            startActivity(intent);
        } else if (i == R.id.addButton){
            Intent intent = new Intent(this, AddObjectScreen.class);
            startActivity(intent);
        }
    }
}
