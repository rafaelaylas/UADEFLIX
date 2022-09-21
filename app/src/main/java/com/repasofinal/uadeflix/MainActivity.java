package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        Intent intent = new Intent(MainActivity.this, SignIn.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() { public void run() { startActivity(intent); finish(); } }, 3000);
    }

    public static Context getContext(){ return context; }
}