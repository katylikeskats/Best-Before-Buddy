package com.bestbeforebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AddObjectScreen extends AppCompatActivity implements View.OnClickListener {
    EditText item;
    RadioGroup buttons;
    DatePicker datePicker;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_object_screen);

        findViewById(R.id.foodCheck).setOnClickListener(this);
        findViewById(R.id.householdCheck).setOnClickListener(this);
        findViewById(R.id.medicationCheck).setOnClickListener(this);
        item = findViewById(R.id.ItemField);
        datePicker = findViewById(R.id.ExpiryField);
        findViewById(R.id.confirmButton).setOnClickListener(this);
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

            return new String[]{new String(Base64.encodeBase64(encrypted)),new String(Base64.encodeBase64(IV))};
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    protected void signIn(String user, String password) throws Exception{
        String[] array = encrypt(password);
        ArrayList<String> out = new HttpLogInRequest().doInBackground(user, array[0], array[1]);
        if (out.get(0).equals("Success")){
            Utils.user = user;
            Utils.password = password;
            Intent intent = new Intent(this, AccountScreen.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.medicationCheck){
            category = "medication";
        } else if (i == R.id.foodCheck){
            category = "food";
        } else if (i == R.id.householdCheck){
            category = "household";
        } else if (i == R.id.confirmButton) {
            try {
                String date = getDateFromDatePicker(datePicker);
                System.out.println("ADD-OBJECT"+date);
                System.out.println(category);
                if (ActionHandler.addObjects(item.getText().toString(), date, category)){
                    Intent intent = new Intent(this, InventoryScreen.class);
                    startActivity(intent);
                }
            } catch (NullPointerException e){

            } catch (IOException e){

            }
        }
    }

    public static String getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        String date = sdf.format(calendar.getTime());

        return date;
    }
}
