package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class CrearContacto extends AppCompatActivity {

    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        email=findViewById(R.id.et_email_nc);
        password=findViewById(R.id.et_password_nc);

    }



}