package com.bestbeforebuddy;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PortIO {
    public static ArrayList<String> read(Socket s) throws IOException {
        if(Utils.key==null) {
            //Creates a BufferedReader that contains the server response
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ArrayList<String> strings = new ArrayList<>();
            String msg;
            //Prints each line of the response
            while (!bufRead.ready()) ;
            while (bufRead.ready() && !(msg = bufRead.readLine()).equals("$")) {
                strings.add(msg);
                while (!bufRead.ready()) ;
            }
            return strings;
        }else{
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
            ArrayList<String> strings = new ArrayList<>();
            String msg;
            //Prints each line of the response
            while (!bufRead.ready()) ;
            while (bufRead.ready() && !(msg = bufRead.readLine()).equals("$")) {
                String IV=msg.substring(0,23);
                String ct=msg.substring(23,msg.length()-1);
                msg=decrypt(Utils.key,Base64.decodeBase64(IV),Base64.decodeBase64(ct));
                strings.add(msg);
                while (!bufRead.ready()) ;
            }
            return strings;
        }
    }

    public static void write(Socket s, ArrayList<String> out) throws IOException {
        if(Utils.key==null) {
            PrintWriter wtr = new PrintWriter(s.getOutputStream());
            for (String strings : out) {
                wtr.println(strings);
            }
            wtr.println("$");
            wtr.flush();
        }else{
            PrintWriter wtr = new PrintWriter(s.getOutputStream());
            for (String strings : out) {
                String enc=encrypt(Utils.key,strings);
                System.out.println("w pt: "+strings);
                System.out.println("w enc:"+enc);
                System.out.println("DBG---------------");
                wtr.println(enc);
            }
            wtr.println("$");
            wtr.flush();
        }
    }

    public static void write(Socket s, String out) throws IOException {
        PrintWriter wtr = new PrintWriter(s.getOutputStream());
        wtr.println(out);
        wtr.println("$");
        wtr.flush();
    }
    private static String decrypt(byte[] key, byte[] initVector, byte[] encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(encrypted);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String encrypt(byte[] key, String value) {
        try {
            byte[] IV = new byte[16];
            new Random().nextBytes(IV);
            IvParameterSpec iv = new IvParameterSpec(IV);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return new String(Base64.encodeBase64(IV))+new String(Base64.encodeBase64(encrypted));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
