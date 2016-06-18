package com.example.andrearodriguez.androidchat.domain;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andrearodriguez on 6/17/16.
 */
public class AvatarDomindHelper {

    private final static String GRAVATAR_URL= "http://www.gravatar.com/avatar/";

    public static String getAvatarUrl (String email) {

        String s = GRAVATAR_URL + md5(email) + "?s=72";
        Log.i("url",s);
        return s;

    }
    private static final String md5(final String s){
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte mesaggeDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for(byte aMessaggeDigest : mesaggeDigest) {
                String h = Integer.toHexString(0xFF & aMessaggeDigest);
                while (h.length()<2)
                    h = "0"+h;
                hexString.append(h);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){

            e.printStackTrace();
        }
        return "";

    }
}
