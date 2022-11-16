package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.User;
import com.repasofinal.uadeflix.support.ActionV;

public class SignUp extends AppCompatActivity {

    private EditText et_name;
    private EditText et_lastName;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_password;

    private ImageButton btn_back;
    private Button btn_signUp;

    private ConstraintLayout clay_loadingScreen;
    private ConstraintLayout clay_userCreatedScreen;
    private TextView tv_error;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initPersonalInfo();

        btn_back = (ImageButton) findViewById(R.id.signUp_ibtn_back);
        btn_signUp = (Button) findViewById(R.id.signUp_btn_nextStep);

        clay_loadingScreen = (ConstraintLayout) findViewById(R.id.signUp_clay_loadingScreen);
        clay_userCreatedScreen = (ConstraintLayout) findViewById(R.id.signUp_clay_userCreatedScreen);
        tv_error = (TextView) findViewById(R.id.signUp_tv_error);

        btn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { startActivity(new Intent(SignUp.this, SignIn.class)); } });
        btn_signUp.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { SignUp(); } });

        clay_loadingScreen.setVisibility(View.GONE);
        clay_userCreatedScreen.setVisibility(View.GONE);
        tv_error.setVisibility(View.GONE);

        //initDev();
    }

    private void initDev() {
        et_name.setText("mobile.test");
        et_lastName.setText("amobile.test");
        et_phone.setText("123");
        et_email.setText("mobile.test@gmail.com");
        et_password.setText("aA#1234567891");
    }

    private void initPersonalInfo() {
        et_name = (EditText) findViewById(R.id.signUp_et_name);
        et_lastName = (EditText) findViewById(R.id.signUp_et_lastName);
        et_phone = (EditText) findViewById(R.id.signUp_et_phone);
        et_email = (EditText) findViewById(R.id.signUp_et_email);
        et_password = (EditText) findViewById(R.id.signUp_et_password);

        et_name.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                et_name.setTextColor(getResources().getColor(R.color.text));
                et_name.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) { }
        });
        et_lastName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                et_lastName.setTextColor(getResources().getColor(R.color.text));
                et_lastName.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) { }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                et_phone.setTextColor(getResources().getColor(R.color.text));
                et_phone.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) { }
        });
        et_email.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                et_email.setTextColor(getResources().getColor(R.color.text));
                et_email.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) { }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                et_password.setTextColor(getResources().getColor(R.color.text));
                et_password.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) { }
        });
    }

    private Boolean checkPersonalInfo() {
        Boolean result = true;

        if(et_email.getText().length() < 8) { et_email.setTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_password.getText().length() < 8) { et_password.setTextColor(getResources().getColor(R.color.error)); result = false; }

        if(et_name.getText().length() == 0) { et_name.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_lastName.getText().length() == 0) { et_lastName.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_phone.getText().length() == 0) { et_phone.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_email.getText().length() == 0) { et_email.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_password.getText().length() == 0) { et_password.setHintTextColor(getResources().getColor(R.color.error)); result = false; }

        if(!result)
        {
            tv_error.setText("Please check all fields in red");
            tv_error.setVisibility(View.VISIBLE);
        }

        return result;
    }

    private void SignUp() {
        clay_loadingScreen.setVisibility(View.VISIBLE);
        if(!checkPersonalInfo()) { clay_loadingScreen.setVisibility(View.GONE); return; }

        User newUser = new User(et_name.getText().toString(), et_lastName.getText().toString(), et_phone.getText().toString(), et_email.getText().toString(), et_password.getText().toString());
        MainActivity.manager.SignUp(
                newUser,
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    clay_userCreatedScreen.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() { public void run() {
                        startActivity(new Intent(SignUp.this, Subscriptions.class));
                        finish();
                    } }, 3000);
                } },
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    tv_error.setText("Please Check the email address or the password");
                    tv_error.setVisibility(View.VISIBLE);
                } },
                new ActionV() { @Override public void Invoke() {
                    tv_error.setText("Connection error");
                    tv_error.setVisibility(View.VISIBLE);
                    clay_loadingScreen.setVisibility(View.GONE);
                } }
                );
    }
}