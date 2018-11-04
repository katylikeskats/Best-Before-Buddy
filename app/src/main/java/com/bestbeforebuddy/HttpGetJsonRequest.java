package com.bestbeforebuddy;

        import android.os.AsyncTask;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;

        import android.net.Uri;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

        import java.io.InputStream;
        import java.io.BufferedInputStream;

        import java.util.ArrayList;
        import java.util.Iterator;

public class HttpGetJsonRequest extends AsyncTask<String, String, ArrayList<ArrayList<String>>> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final String stringUrl = "192.168.10.207:8080";

    @Override
    protected ArrayList<ArrayList<String>> doInBackground(String... strings) {

        StringBuilder result = new StringBuilder(); //Store json string from server
        ArrayList<ArrayList<String>> userData = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 3; i++) {
            userData.add(new ArrayList<String>()); //Make three lists for data storage (item,
        }
        String user = strings[0]; //User desired
        JSONObject jobj; //Stores json for user data

        try {
            String uri = new Uri.Builder()
                    .scheme("http")
                    .authority(stringUrl)
                    .appendQueryParameter("username", user)
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

            InputStream in = new BufferedInputStream(connection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException e){
            System.out.println(e.toString());
        } catch (IOException e){
            e.printStackTrace();
            result.append("Failed");
        }

        //Get json data into list of lists
        try {
            jobj = new JSONObject(result.toString()); //Make json object with json string
            Iterator<String> keys = jobj.keys();
            String tempKey = null;
            while (keys.hasNext()) {
                tempKey = keys.next();
                userData.get(0).add(tempKey);
                userData.get(1).add(jobj.getJSONObject("expiryDate").getString(tempKey));
                userData.get(2).add(jobj.getJSONObject("category").getString(tempKey));
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        }

        return userData; //Return user data

    }

    /*
    @Override
    protected String doInBackground(String... strings) {
    String user = strings[0];
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

    }*/
}