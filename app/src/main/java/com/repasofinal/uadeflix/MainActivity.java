package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

        email.setText("test");
        password.setText("1234");

    }

    public void Login(View view) {

        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Ingrese credenciales!", Toast.LENGTH_LONG).show();
        }else{
            if(email.getText().toString().equals("test") &&
            password.getText().toString().equals("1234")){
                Toast.makeText(getApplicationContext(), "Usuario logueado!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, Catalogo.class);
                startActivity(i);
            }else
            {
                Toast.makeText(getApplicationContext(), "Credenciales incorrectas!", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void CrearContacto(View view) {
    }
}