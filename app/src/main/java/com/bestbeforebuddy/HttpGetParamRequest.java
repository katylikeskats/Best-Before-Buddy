package com.bestbeforebuddy;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetParamRequest extends AsyncTask<String, Void, String> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;
   // private static final String stringUrl = "192.168.10.207:8080";

    @Override
    protected String doInBackground(String... strings) {
        String result = "Failed";
        String user = strings[0];
        String password = strings[1].replace("+", "%2B");
        String iv = strings[2].replace("+", "%2B");
        String inputLine;

        try {

            String url = "http://192.168.10.207:8080/Best_Before_Buddy/loginForm?username="+user
                    +"&password="+password+"&IV="+iv;
            Log.e("url",url);
            URL myUrl = new URL(url);

            //Creates a connection
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

            //Sets properties
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to the URL yay
            connection.connect();

            //create input stream reader
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

            //create buffered reader and string builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //Check if the in we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

            result = stringBuilder.toString();


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
