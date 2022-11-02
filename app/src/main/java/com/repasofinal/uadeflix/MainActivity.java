package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.repasofinal.uadeflix.activities.Catalog;
import com.repasofinal.uadeflix.activities.SignIn;
import com.repasofinal.uadeflix.logic.Manager;
import com.repasofinal.uadeflix.support.ActionV;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    public static Manager manager;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        manager = new Manager();

        new Handler().postDelayed(new Runnable() { public void run() { RefreshSession(); } }, 3000);
    }

    public static Context getContext(){ return context; }
    public void RefreshSession(){
        SharedPreferences prefs = getSharedPreferences("Uadeflix", MODE_PRIVATE);
        String refreshToken = prefs.getString("session", null);
        if (refreshToken != null) {
            MainActivity.manager.RefreshUser(
                    refreshToken,
                    new ActionV() { @Override public void Invoke()
                    {
                        startActivity(new Intent(MainActivity.this, Catalog.class));
                        manager.AutomaticTokenRefresh();
                        finish();
                    } },
                    new ActionV() { @Override public void Invoke() { startActivity(new Intent(MainActivity.this, SignIn.class)); finish();} },
                    new ActionV() { @Override public void Invoke() { startActivity(new Intent(MainActivity.this, SignIn.class)); finish();} }
            );
        } else { startActivity(new Intent(MainActivity.this, SignIn.class)); finish(); }
    }
}