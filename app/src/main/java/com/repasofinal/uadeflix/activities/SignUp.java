package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.enums.PLAN;
import com.repasofinal.uadeflix.logic.Card;
import com.repasofinal.uadeflix.logic.User;
import com.repasofinal.uadeflix.support.ActionV;

import java.util.ArrayList;

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

    private ConstraintLayout clay_loadingScreen;
    private ConstraintLayout clay_userCreatedScreen;
    private ConstraintLayout clay_errorScreen;
    private TextView tv_error;

    private int currentStep = 1;

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

        clay_loadingScreen = (ConstraintLayout) findViewById(R.id.signUp_clay_loadingScreen);
        clay_userCreatedScreen = (ConstraintLayout) findViewById(R.id.signUp_clay_userCreatedScreen);
        clay_errorScreen = (ConstraintLayout) findViewById(R.id.signUp_clay_errorScreen);
        tv_error = (TextView) findViewById(R.id.signUp_tv_error);

        btn_prevStep.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { prevStep(); } });
        btn_nextStep.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { nextStep(); } });

        rbtn_silver.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { selectPlan(rbtn_silver); } });
        rbtn_gold.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { selectPlan(rbtn_gold); } });
        rbtn_platinium.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { selectPlan(rbtn_platinium); } });

        et_cardNumber.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                et_cardNumber.setTextColor(getResources().getColor(R.color.text));
                et_cardNumber.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) {
                if(et_cardNumber.getText().length() > 16) { et_cardNumber.setText(et_cardNumber.getText().subSequence(0, 16)); }
            }
        });
        et_holdersName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et_holdersName.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) {
                if(et_holdersName.getText().length() > 16) { et_holdersName.setText(et_holdersName.getText().subSequence(0, 16)); }
            }
        });
        et_dueDate.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et_dueDate.setTextColor(getResources().getColor(R.color.text));
                et_dueDate.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) {
                if(et_dueDate.getText().length() > 4) { et_dueDate.setText(et_dueDate.getText().subSequence(0, 4)); }
            }
        });
        et_secCode.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et_secCode.setTextColor(getResources().getColor(R.color.text));
                et_secCode.setHintTextColor(getResources().getColor(R.color.text));
            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void afterTextChanged(Editable editable) {
                if(et_secCode.getText().length() > 3) { et_secCode.setText(et_secCode.getText().subSequence(0, 3)); }
            }
        });

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

        clay_loadingScreen.setVisibility(View.GONE);
        clay_userCreatedScreen.setVisibility(View.GONE);
        clay_errorScreen.setVisibility(View.GONE);

        selectPlan(rbtn_silver);
        setStep();
    }

    private void prevStep() { currentStep--; setStep(); }
    private void nextStep() { currentStep++; setStep(); }
    private void setStep() {
        switch (currentStep){
            case 0: finish(); break;
            case 1: goToPersonalInfo(); break;
            case 2: if(checkPersonalInfo()) { goToPaymentInfo(); } else { currentStep--; } break;
            case 3: if(checkPaymentInfo()) { goToPlanInfo(); } else { currentStep--; } break;
            case 4: submitRequest(); break;
        }
    }
    private void goToPersonalInfo() {
        clay_personalInformation.setVisibility(View.VISIBLE);
        clay_paymentInfo.setVisibility(View.GONE);
        clay_planInformation.setVisibility(View.GONE);

        btn_prevStep.setText("Back");
        btn_nextStep.setText("Next");
    }
    private void goToPaymentInfo() {
        clay_personalInformation.setVisibility(View.GONE);
        clay_paymentInfo.setVisibility(View.VISIBLE);
        clay_planInformation.setVisibility(View.GONE);

        btn_prevStep.setText("Back");
        btn_nextStep.setText("Last");
    }
    private Boolean checkPersonalInfo() {
        Boolean result = true;

        if(et_email.getText().length() < 8) { et_email.setTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_password.getText().length() < 12) { et_password.setTextColor(getResources().getColor(R.color.error)); result = false; }

        if(et_name.getText().length() == 0) { et_name.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_lastName.getText().length() == 0) { et_lastName.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_phone.getText().length() == 0) { et_phone.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_email.getText().length() == 0) { et_email.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_password.getText().length() == 0) { et_password.setHintTextColor(getResources().getColor(R.color.error)); result = false; }

        if(!result)
        {
            tv_error.setText("Please check all fields in red");
            clay_errorScreen.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() { public void run() { clay_errorScreen.setVisibility(View.GONE); } }, 2000);
        }

        return result;
    }
    private Boolean checkPaymentInfo() {
        Boolean result = true;

        if(et_cardNumber.getText().length() < 16) { et_cardNumber.setTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_dueDate.getText().length() < 4) { et_dueDate.setTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_secCode.getText().length() < 3) { et_secCode.setTextColor(getResources().getColor(R.color.error)); result = false; }

        if(et_cardNumber.getText().length() == 0) { et_cardNumber.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_holdersName.getText().length() == 0) { et_holdersName.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_dueDate.getText().length() == 0) { et_dueDate.setHintTextColor(getResources().getColor(R.color.error)); result = false; }
        if(et_secCode.getText().length() == 0) { et_secCode.setHintTextColor(getResources().getColor(R.color.error)); result = false; }

        if(!result)
        {
            tv_error.setText("Please check all fields in red");
            clay_errorScreen.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() { public void run() { clay_errorScreen.setVisibility(View.GONE); } }, 2000);
        }

        return result;
    }

    private void goToPlanInfo() {
        clay_personalInformation.setVisibility(View.GONE);
        clay_paymentInfo.setVisibility(View.GONE);
        clay_planInformation.setVisibility(View.VISIBLE);

        btn_prevStep.setText("Back");
        btn_nextStep.setText("Sign Up");
    }
    private void selectPlan(RadioButton selectedButton) {
        rbtn_silver.setChecked(false);
        rbtn_gold.setChecked(false);
        rbtn_platinium.setChecked(false);

        rbtn_silver.setBackgroundColor(getResources().getColor(R.color.primary));
        rbtn_gold.setBackgroundColor(getResources().getColor(R.color.primary));
        rbtn_platinium.setBackgroundColor(getResources().getColor(R.color.primary));

        selectedButton.setChecked(true);
        selectedButton.setBackgroundColor(getResources().getColor(R.color.back));
    }
    private PLAN[] getPlan() {
        ArrayList<PLAN> plan = new ArrayList<>();
        if(rbtn_silver.isChecked()){ plan.add(PLAN.SILDVER); }
        if(rbtn_gold.isChecked()){ plan.add(PLAN.GOLD); }
        if(rbtn_platinium.isChecked()){ plan.add(PLAN.PLATINUM); }
        return (PLAN[]) plan.toArray();
    }

    private void submitRequest() {
        clay_loadingScreen.setVisibility(View.VISIBLE);
        Card newCard = new Card(et_cardNumber.getText().toString(), et_holdersName.getText().toString(), et_dueDate.getText().toString(), et_secCode.getText().toString());
        User newUser = new User(et_name.getText().toString(), et_lastName.getText().toString(), et_phone.getText().toString(), et_email.getText().toString(), et_password.getText().toString(), newCard, getPlan());
        MainActivity.manager.SignUp(
                newUser,
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    clay_userCreatedScreen.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() { public void run() { finish(); } }, 3000);
                } },
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);

                    tv_error.setText("Please change the email address");
                    clay_errorScreen.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() { public void run() { clay_errorScreen.setVisibility(View.GONE); } }, 2000);
                } },
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                } }
                );



    }
}