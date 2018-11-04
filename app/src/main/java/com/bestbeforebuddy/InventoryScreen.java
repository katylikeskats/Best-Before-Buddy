package com.bestbeforebuddy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class InventoryScreen extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String[]> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_screen);

        //buttons
        findViewById(R.id.notificationButton).setOnClickListener(this);
        findViewById(R.id.accountButton).setOnClickListener(this);
        findViewById(R.id.inventoryButton).setOnClickListener(this);
        findViewById(R.id.addButton).setOnClickListener(this);

        ArrayList<String[]> list = new ArrayList<>();
        try {
            list = ActionHandler.getTable();
        } catch (IOException e) {
        }

        if (list.get(0)[0].equals("Failed")) {
            return;
        }

        ListView listView = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, R.layout.list_item_layout, list);
        listView.setAdapter(adapter);
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

    private class MyAdapter extends ArrayAdapter<String[]>{
        private Context mContext;
        private int resource;
        public MyAdapter(Context context, int resource, ArrayList<String[]> objects){
            super(context,resource,objects);
            mContext = context;
            this.resource = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            String item = "";
            String expiryDate = "";
            String category = "";
            String daysLeft = "";
            item = getItem(position)[0];
            expiryDate = getItem(position)[1];
            category = getItem(position)[2];
            daysLeft = getItem(position)[3];


            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(resource, parent, false);

            TextView itemText = (TextView) convertView.findViewById(R.id.textView1);
            TextView expiryDateText = (TextView) convertView.findViewById(R.id.textView2);
            TextView daysToGo = (TextView) convertView.findViewById(R.id.textView3);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
            itemText.setText(item);
            expiryDateText.setText(expiryDate);
            daysToGo.setText(daysLeft);
            Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.apple, null);;
            if (category.equals("food")) {
                drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.apple, null);
            } else if (category.equals("medication")){
                drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.pill, null);
            } else if (category.equals("household")){
                drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.tide, null);
            }
            imageView.setImageDrawable(drawable);

            return convertView;
        }
    }

}
