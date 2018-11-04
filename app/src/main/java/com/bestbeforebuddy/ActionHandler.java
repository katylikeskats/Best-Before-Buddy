package com.bestbeforebuddy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ActionHandler {
    public static void init() throws IOException {
        PortIO.write( Utils.s, "key-exchange-1");
        ArrayList<String> received = PortIO.read(Utils.s);
        int ret=Utils.middle(new int[]{Integer.parseInt(received.get(0)),Integer.parseInt(received.get(1)),Integer.parseInt(received.get(2))});
        PortIO.write(Utils.s, "key-exchange-3\n"+ret);
        Utils.end();
        System.out.println(Utils.key);
    }

    public static boolean addObjects(String item, String expiryDate, String category) throws IOException{
        ArrayList<String> out = new ArrayList<>();
        out.add("add-item");
        out.add(Utils.user);
        String[] pass = Utils.encrypt(Utils.password);
        out.add(pass[0]);
        out.add(pass[1]);
        out.add(item);
        out.add(expiryDate);
        out.add(category);
        PortIO.write(Utils.s, out);
        ArrayList<String> received = PortIO.read(Utils.s);
        return (received.get(0).equals("Success"));
        
    }
}
