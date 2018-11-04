package com.bestbeforebuddy;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HttpLogInRequest extends AsyncTask<String, Void, ArrayList<String>> {
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        String user = strings[0];
        String password = strings[1];
        String iv = strings[2];
        ArrayList<String> out = new ArrayList<>();

        try {
            Socket s = new Socket("192.168.10.207", 8080);
            //Instantiates a new PrintWriter passing in the sockets output stream
            PrintWriter wtr = new PrintWriter(s.getOutputStream());

            //Prints the request string to the output stream
            wtr.println("login");
            wtr.println(user);
            wtr.println(password);
            wtr.println(iv);
            wtr.println("$");
            wtr.flush();

            out = PortIO.read(s);
            System.out.println("HTTPLOGIN"+out.size());
            ActionHandler.init();
        } catch (IOException e){
            e.printStackTrace();
        }

        return out;
    }

}
