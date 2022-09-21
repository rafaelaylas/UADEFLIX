package com.repasofinal.uadeflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;
    private Button btn_signIn;
    private TextView txt_message;
    private TextView btn_signUp;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_email = (EditText) findViewById(R.id.signIn_et_email);
        et_password = (EditText) findViewById(R.id.signIn_et_password);
        btn_signIn = (Button) findViewById(R.id.signIn_btn_signIn);
        btn_signUp = (TextView) findViewById(R.id.signIn_tbtn_signUp);
        txt_message = (TextView) findViewById(R.id.signIn_txt_message);

        btn_signIn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { signIn(et_email.getText().toString(), et_password.getText().toString()); } });
        btn_signUp.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { signUp(); } });

        txt_message.setVisibility(View.GONE);
    }

    private void signIn(String email, String password) { startActivity(new Intent(SignIn.this, Catalog.class)); }
    private void signUp() { startActivity(new Intent(SignIn.this, SignUp.class)); }
}