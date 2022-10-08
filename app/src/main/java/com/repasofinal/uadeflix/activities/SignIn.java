package com.repasofinal.uadeflix.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.User;
import com.repasofinal.uadeflix.support.ActionV;

public class SignIn extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;
    private Button btn_signIn;
    private TextView txt_message;
    private TextView btn_signUp;
    private ConstraintLayout clay_loadingScreen;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_email = (EditText) findViewById(R.id.signIn_et_email);
        et_password = (EditText) findViewById(R.id.signIn_et_password);
        btn_signIn = (Button) findViewById(R.id.signIn_btn_signIn);
        btn_signUp = (TextView) findViewById(R.id.signIn_tbtn_signUp);
        txt_message = (TextView) findViewById(R.id.signIn_txt_message);
        clay_loadingScreen = (ConstraintLayout) findViewById(R.id.signIn_clay_loadingScreen);

        btn_signIn.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { signIn(et_email.getText().toString(), et_password.getText().toString()); } });
        btn_signUp.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { signUp(); } });

        txt_message.setVisibility(View.GONE);
        clay_loadingScreen.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txt_message.setVisibility(View.GONE);
        clay_loadingScreen.setVisibility(View.GONE);
    }

    private void signIn(String email, String password) {
        clay_loadingScreen.setVisibility(View.VISIBLE);
        MainActivity.manager.SignIn(
                new User(email, password),
                new ActionV() { @Override public void Invoke() {
                        et_email.setText("");
                        et_password.setText("");
                        startActivity(new Intent(SignIn.this, Catalog.class));
                    }
                },
                new ActionV() { @Override public void Invoke() {
                        txt_message.setText("Incorrect email or password");
                        txt_message.setVisibility(View.VISIBLE);
                        clay_loadingScreen.setVisibility(View.GONE);
                    }
                },
                new ActionV() { @Override public void Invoke() {
                    txt_message.setText("Connection error");
                    txt_message.setVisibility(View.VISIBLE);
                    clay_loadingScreen.setVisibility(View.GONE);
                    }
                }
        );
    }
    private void signUp() { startActivity(new Intent(SignIn.this, SignUp.class)); }
}