package com.bestbeforebuddy;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {
    public static String user;
    public static String password;
    public static Socket s;
    private static int thatE;
    private static int[] bothNum = new int[3];
    public static int keySeed;
    public static byte[] key;

    public static void init() throws IOException {
        s = new Socket("192.168.10.207", 8080);
        System.out.println("UTILS"+s);
    }

    public static int middle(int[] num) { // their side
        bothNum[0]=num[0];
        bothNum[1]=num[1];
        bothNum[2]=num[2];
        Random rand = new Random(); // generate a random number
        thatE = rand.nextInt(10000) + 1;
        return (int) powfast((long) bothNum[0], (long) thatE, (long) bothNum[1]);
    }

    public static void end() {
        keySeed= (int) powfast((long) bothNum[2], (long) thatE, (long) bothNum[1]);
        System.out.println("UTILS"+keySeed);
        key=new byte[16];
        new Random(keySeed).nextBytes(key);
        System.out.println(new String(Base64.encodeBase64(key)));
    }

    private static long powfast(long a, long b, long mod) {
        long res = 1;
        long curr = a;
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= curr;
                res %= mod;
            }
            curr *= curr;
            curr %= mod;
            b >>= 1;
        }
        return res;

    }

    public static ArrayList<String[]> convert(ArrayList<String> text){
        ArrayList<String[]> table=new ArrayList<>();
        for(String s:text) {
            table.add(s.split(","));
        }
        return table;
    }

    public static String[] encrypt(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            byte[] IV = new byte[16];
            new Random().nextBytes(IV);
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



}
