package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.repasofinal.uadeflix.R;

public class SignUp extends AppCompatActivity {

    private ConstraintLayout clay_personalInformation;
    private EditText et_name;
    private EditText et_lastName;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_password;

    private ConstraintLayout clay_paymentInfo;
    private EditText et_cardNumber;
    private EditText et_holdersName;
    private EditText et_dueDate;
    private EditText et_secCode;

    private ConstraintLayout clay_planInformation;
    private RadioButton rbtn_silver;
    private RadioButton rbtn_gold;
    private RadioButton rbtn_platinium;

    private Button btn_prevStep;
    private Button btn_nextStep;

    private int currentStep = 0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        clay_personalInformation = (ConstraintLayout) findViewById(R.id.signUp_clay_personalInformation);
        et_name = (EditText) findViewById(R.id.signUp_et_name);
        et_lastName = (EditText) findViewById(R.id.signUp_et_lastName);
        et_phone = (EditText) findViewById(R.id.signUp_et_phone);
        et_email = (EditText) findViewById(R.id.signUp_et_email);
        et_password = (EditText) findViewById(R.id.signUp_et_password);

        clay_paymentInfo = (ConstraintLayout) findViewById(R.id.signUp_clay_paymentInfo);
        et_cardNumber = (EditText) findViewById(R.id.signUp_et_cardNumber);
        et_holdersName = (EditText) findViewById(R.id.signUp_et_holdersName);
        et_dueDate = (EditText) findViewById(R.id.signUp_et_dueDate);
        et_secCode = (EditText) findViewById(R.id.signUp_et_secCode);

        clay_planInformation = (ConstraintLayout) findViewById(R.id.signUp_clay_planInformation);
        rbtn_silver = (RadioButton) findViewById(R.id.signUp_rbtn_silver);
        rbtn_gold = (RadioButton) findViewById(R.id.signUp_rbtn_gold);
        rbtn_platinium = (RadioButton) findViewById(R.id.signUp_rbtn_platinium);

        btn_prevStep = (Button) findViewById(R.id.signUp_btn_prevStep);
        btn_nextStep = (Button) findViewById(R.id.signUp_btn_nextStep);

        btn_prevStep.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { prevStep(); } });
        btn_nextStep.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { nextStep(); } });

        setStep();
    }

    private void submitRequest() { finish(); }
    private void prevStep() { currentStep--; setStep(); }
    private void nextStep() { currentStep++; setStep(); }
    private void setStep() {
        switch (currentStep){
            case 0: goToPersonalInfo(); break;
            case 1: goToPaymentInfo(); break;
            case 2: goToPlanInfo(); break;
            case 3: submitRequest(); break;
        }
    }
    private void goToPersonalInfo() {
        clay_personalInformation.setVisibility(View.VISIBLE);
        clay_paymentInfo.setVisibility(View.GONE);
        clay_planInformation.setVisibility(View.GONE);

        btn_prevStep.setVisibility(View.GONE);
        btn_nextStep.setVisibility(View.VISIBLE);
    }
    private void goToPaymentInfo() {
        clay_personalInformation.setVisibility(View.GONE);
        clay_paymentInfo.setVisibility(View.VISIBLE);
        clay_planInformation.setVisibility(View.GONE);

        btn_prevStep.setVisibility(View.VISIBLE);
        btn_nextStep.setVisibility(View.VISIBLE);
    }
    private void goToPlanInfo() {
        clay_personalInformation.setVisibility(View.GONE);
        clay_paymentInfo.setVisibility(View.GONE);
        clay_planInformation.setVisibility(View.VISIBLE);

        btn_prevStep.setVisibility(View.VISIBLE);
        btn_nextStep.setVisibility(View.VISIBLE);
    }
}