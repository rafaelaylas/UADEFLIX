package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.repasofinal.uadeflix.activities.SignIn;
import com.repasofinal.uadeflix.logic.Manager;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    public static Manager manager;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        manager = new Manager();

        Intent intent = new Intent(MainActivity.this, SignIn.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() { public void run() { startActivity(intent); finish(); } }, 3000);
    }

    public static Context getContext(){ return context; }
}