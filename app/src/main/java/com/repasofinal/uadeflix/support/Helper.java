package com.repasofinal.uadeflix.support;

import android.widget.Toast;

import com.repasofinal.uadeflix.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void Toast(String mensaje) { Toast.makeText(MainActivity.getContext(), mensaje,Toast.LENGTH_LONG).show(); }
    public static <T> ArrayList<T> listToArrayList(List<T> list) {
        ArrayList<T> arl = new ArrayList<T>();
        for (T object : list) { arl.add((T) object); }
        return arl;
    }
}
