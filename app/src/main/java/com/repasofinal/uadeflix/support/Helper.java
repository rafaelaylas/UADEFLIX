package com.repasofinal.uadeflix.support;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.repasofinal.uadeflix.MainActivity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void Toast(String mensaje) { Toast.makeText(MainActivity.getContext(), mensaje,Toast.LENGTH_LONG).show(); }
    public static <T> ArrayList<T> listToArrayList(List<T> list) {
        ArrayList<T> arl = new ArrayList<T>();
        for (T object : list) { arl.add((T) object); }
        return arl;
    }
    public static String[] DecodeJWT(String JWTEncoded) {
            String[] split = JWTEncoded.split("\\.");
            for (int i = 0; i< split.length; i++) { split[i] = GetJson(split[i]); }
            return split;
    }
    private static String GetJson(String strEncoded) {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        try { return new String(decodedBytes, "UTF-8"); }
        catch (UnsupportedEncodingException e) { e.printStackTrace(); }
        return "";
    }

}
