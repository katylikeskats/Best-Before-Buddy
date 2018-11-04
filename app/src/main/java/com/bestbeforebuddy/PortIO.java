package com.bestbeforebuddy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class PortIO {
    public static ArrayList<String> read(Socket s) throws IOException {
        //Creates a BufferedReader that contains the server response
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ArrayList<String> strings = new ArrayList<>();
        String msg;
        //Prints each line of the response
        while(!bufRead.ready());
        while(bufRead.ready() && !(msg = bufRead.readLine()).equals("$")){
            strings.add(msg);
            while(!bufRead.ready());
        }
        return strings;
    }

    public static void write(Socket s, ArrayList<String> out) throws IOException {
        PrintWriter wtr = new PrintWriter(s.getOutputStream());
        for (String strings : out){
            wtr.println(strings);
        }
        wtr.println("$");
        wtr.flush();
    }

    public static void write(Socket s, String out) throws IOException {
        PrintWriter wtr = new PrintWriter(s.getOutputStream());
        wtr.println(out);
        wtr.println("$");
        wtr.flush();
    }
}
