package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.Card;
import com.repasofinal.uadeflix.support.ActionV;
import com.repasofinal.uadeflix.logic.Subscription;
import com.repasofinal.uadeflix.support.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Subscriptions extends AppCompatActivity {

    private ScrollView scrll_paymentInfo;
    private EditText et_cardNumber;
    private EditText et_holdersName;
    private EditText et_dueDate;
    private EditText et_secCode;

    private ScrollView scrll_planInformation;
    private LinearLayout planLayout;
    private ArrayList<CheckBox> checkBoxes;

    private ImageButton btn_back;
    private Button btn_nextStep;

    private ConstraintLayout clay_loadingScreen;
    private ConstraintLayout clay_userCreatedScreen;
    private TextView tv_error;

    private ConstraintLayout clay_menu;
    private ImageButton btn_refresh;

    private int currentStep = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        initPaymentInfo();
        initPlanInfo();

        btn_back = (ImageButton) findViewById(R.id.sub_ibtn_back);
        btn_nextStep = (Button) findViewById(R.id.sub_btn_nextStep);

        clay_loadingScreen = (ConstraintLayout) findViewById(R.id.sub_clay_loadingScreen);
        clay_userCreatedScreen = (ConstraintLayout) findViewById(R.id.sub_clay_userCreatedScreen);
        tv_error = (TextView) findViewById(R.id.sub_tv_error);

        clay_menu = (ConstraintLayout) findViewById(R.id.sub_clay_menu);
        btn_refresh = (ImageButton) findViewById(R.id.sub_btn_refresh);

        btn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { prevStep(); } });
        btn_nextStep.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { nextStep(); } });
        btn_refresh.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { SignIn(); } });

        btn_refresh.setVisibility(View.GONE);
        clay_menu.setVisibility(View.GONE);
        clay_loadingScreen.setVisibility(View.GONE);
        clay_userCreatedScreen.setVisibility(View.GONE);
        tv_error.setVisibility(View.GONE);

        if(MainActivity.manager.GetCurrentUser().getToken() == null) { SignIn(); }
        else { clay_menu.setVisibility(View.VISIBLE); }

        //initDev();
    }

    private void prevStep() { currentStep--; setStep(); }
    private void nextStep() { currentStep++; setStep(); }
    private void setStep() {
        switch (currentStep){
            case 0: End(); break;
            case 1: goToPaymentInfo(); break;
            case 2: if(checkPaymentInfo()) { goToPlanInfo(); } else { currentStep--; } break;
            case 3: Subscribe(); break;
        }
    }

    private void initDev() {
        et_cardNumber.setText("1234567890123456");
        et_holdersName.setText("1234567890123456");
        et_dueDate.setText("1212");
        et_secCode.setText("123");
    }

    private void initPaymentInfo() {
        scrll_paymentInfo = (ScrollView) findViewById(R.id.sub_scrll_paymentInfo);
        et_cardNumber = (EditText) findViewById(R.id.sub_et_cardNumber);
        et_holdersName = (EditText) findViewById(R.id.sub_et_holdersName);
        et_dueDate = (EditText) findViewById(R.id.sub_et_dueDate);
        et_secCode = (EditText) findViewById(R.id.sub_et_secCode);

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
    }
    private void initPlanInfo() {
        scrll_planInformation = (ScrollView) findViewById(R.id.sub_scrll_planInformation);
        planLayout = (LinearLayout) findViewById(R.id.sub_rlay_planInformation);

        MainActivity.manager.UpdateSubscriptions(
                new ActionV() { @Override public void Invoke() {
                    Subscription[] subscriptions = MainActivity.manager.GetSubscriptions();
                    LayoutInflater inflater = LayoutInflater.from(Subscriptions.this);
                    checkBoxes = new ArrayList<CheckBox>();
                    for(int i = 0; i < subscriptions.length; i++)
                    {
                        View newView = createRBTN(subscriptions[i], inflater);
                        planLayout.addView(newView);
                        checkBoxes.add(newView.findViewById(R.id.sub_ckbx));
                    }
                } },
                new ActionV() { @Override public void Invoke() { } },
                new ActionV() { @Override public void Invoke() { } }
        );
    }

    private void goToPaymentInfo() {
        scrll_paymentInfo.setVisibility(View.VISIBLE);
        scrll_planInformation.setVisibility(View.GONE);

        btn_nextStep.setText("Next Step");
    }
    private void goToPlanInfo() {
        scrll_paymentInfo.setVisibility(View.GONE);
        scrll_planInformation.setVisibility(View.VISIBLE);

        btn_nextStep.setText("Subscribe");
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
            tv_error.setVisibility(View.VISIBLE);
        }

        return result;
    }
    private void selectPlan(CheckBox checkBox) {
        if(checkBox.isChecked()) { checkBox.setBackgroundColor(getResources().getColor(R.color.back)); }
        else { checkBox.setBackgroundColor(getResources().getColor(R.color.primary)); }
    }
    private View createRBTN(Subscription subscription, LayoutInflater inflater) {
        View newRBTN = inflater.inflate(R.layout.sub_ckbx, null);

        CheckBox checkBox = (CheckBox) newRBTN.findViewById(R.id.sub_ckbx);
        ImageView icon = (ImageView) newRBTN.findViewById(R.id.sub_ckbx_icon);

        TextView title = (TextView) newRBTN.findViewById(R.id.sub_ckbx_title);
        TextView description = (TextView) newRBTN.findViewById(R.id.sub_ckbx_description);
        TextView price = (TextView) newRBTN.findViewById(R.id.sub_ckbx_price);

        //Log.d("Response", subscription.GetImage());
        Picasso.get().load(subscription.GetImage()).placeholder(R.drawable.ic_movie_placeholder).into(icon);
        checkBox.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { selectPlan(checkBox); } });
        title.setText(subscription.GetTitle());
        description.setText(subscription.GetDescription());
        price.setText("$ " + subscription.GetPrice());

        return newRBTN;
    }

    private int[] getPlan() {
        Subscription[] subscriptions = MainActivity.manager.GetSubscriptions();
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < checkBoxes.size(); i++) {
            if(checkBoxes.get(i).isChecked()) {
                list.add(subscriptions[i].GetId());
                Log.d("Response", subscriptions[i].GetId() + ": " + subscriptions[i].GetTitle());
            }
        }

        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++) { result[i] = list.get(i); }

        return result;
    }
    private void Subscribe() {
        clay_loadingScreen.setVisibility(View.VISIBLE);
        Card newCard = new Card(et_cardNumber.getText().toString(), et_holdersName.getText().toString(), et_dueDate.getText().toString(), et_secCode.getText().toString());

        MainActivity.manager.Subscribe(
                getPlan(),
                new ActionV() { @Override public void Invoke() {
                    clay_loadingScreen.setVisibility(View.GONE);
                    clay_userCreatedScreen.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() { public void run() {
                        End();
                    } }, 3000);
                } },
                new ActionV() { @Override public void Invoke() {
                    prevStep();
                    clay_loadingScreen.setVisibility(View.GONE);
                    tv_error.setText("Please Check the Card Information");
                    tv_error.setVisibility(View.VISIBLE);
                } },
                new ActionV() { @Override public void Invoke() {
                    prevStep();
                    clay_loadingScreen.setVisibility(View.GONE);
                    tv_error.setText("Connection Error");
                    tv_error.setVisibility(View.VISIBLE);
                } }
        );
    }
    private void SignIn() {
        clay_loadingScreen.setVisibility(View.VISIBLE);
        MainActivity.manager.SignIn(MainActivity.manager.GetCurrentUser(),
                new ActionV() { @Override public void Invoke() {
                    btn_refresh.setVisibility(View.GONE);
                    clay_loadingScreen.setVisibility(View.GONE);
                    clay_menu.setVisibility(View.VISIBLE);
                } },
                new ActionV() { @Override public void Invoke() {
                    btn_refresh.setVisibility(View.VISIBLE);
                    clay_loadingScreen.setVisibility(View.GONE);
                }
                },
                new ActionV() { @Override public void Invoke() {
                    btn_refresh.setVisibility(View.VISIBLE);
                    clay_loadingScreen.setVisibility(View.GONE);
                }
                }
        );
    }
    private void End() {
        if(MainActivity.manager.GetCurrentUser().getToken() == null) { startActivity(new Intent(Subscriptions.this, SignIn.class)); }
        else { startActivity(new Intent(Subscriptions.this, Catalog.class)); }
        finish();
    }
}