package com.bestbeforebuddy;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.net.Uri;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetJsonRequest extends AsyncTask<String, String, String> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final String stringUrl = "192.168.10.207:8080";
    @Override
    protected String doInBackground(String... strings) {
        JSONParser jsonParser = new JSONParse();
        JSONObject jobj = jsonparser.make
        try {

            String uri = new Uri.Builder()
                    .scheme("http")
                    .authority(stringUrl)
                    .appendQueryParameter("username", user)
                    .appendQueryParameter("password", password)
                    .build()
                    .toString();
            URL myUrl = new URL(uri);

            //Creates a connection
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

            //Sets properties
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to the URL yay
            connection.connect();

            onnection.


        } catch (MalformedURLException e){
            System.out.println(e.toString());
        } catch (IOException e){
            e.printStackTrace();
            result = "Failed";
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
























+