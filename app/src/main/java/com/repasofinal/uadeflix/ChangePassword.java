package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangePassword extends AppCompatActivity {

    private ImageButton ibtn_back;
    private EditText et_oldPassword;
    private EditText et_newPassword;
    private EditText et_repeatPassword;
    private TextView txt_message;
    private ImageView iv_update;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ibtn_back = (ImageButton) findViewById(R.id.chgPass_ibtn_back);
        et_oldPassword = (EditText) findViewById(R.id.chgPass_et_oldPassword);
        et_newPassword = (EditText) findViewById(R.id.chgPass_et_newPassword);
        et_repeatPassword = (EditText) findViewById(R.id.chgPass_et_repeatPassword);
        txt_message = (TextView) findViewById(R.id.chgPass_txt_message);
        iv_update = (ImageView) findViewById(R.id.chgPass_iv_update);

        iv_update.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePass(et_oldPassword.getText().toString(), et_newPassword.getText().toString(), et_repeatPassword.getText().toString()); } });
        ibtn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { finish(); } });

        txt_message.setVisibility(View.GONE);
    }

    private void ChangePass(String oldPass, String newPass, String repeatPass)
    {
        if(newPass.equals(repeatPass)) {
            txt_message.setText("The new password must be equal to the repeated password");
            txt_message.setVisibility(View.VISIBLE);
            return;
        }
        finish();
    }
}