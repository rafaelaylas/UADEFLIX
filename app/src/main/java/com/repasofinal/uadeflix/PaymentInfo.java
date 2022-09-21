package com.repasofinal.uadeflix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PaymentInfo extends AppCompatActivity {

    private ImageButton ibtn_back;
    private EditText et_cardNumber;
    private EditText et_holdersName;
    private EditText et_dueDate;
    private EditText et_secCode;
    private TextView txt_message;
    private ImageView iv_update;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        ibtn_back = (ImageButton) findViewById(R.id.paymentInfo_ibtn_back);
        et_cardNumber = (EditText) findViewById(R.id.paymentInfo_et_cardNumber);
        et_holdersName = (EditText) findViewById(R.id.paymentInfo_et_holdersName);
        et_dueDate = (EditText) findViewById(R.id.paymentInfo_et_dueDate);
        et_secCode = (EditText) findViewById(R.id.paymentInfo_et_secCode);
        txt_message = (TextView) findViewById(R.id.paymentInfo_txt_message);
        iv_update = (ImageView) findViewById(R.id.paymentInfo_iv_update);

        iv_update.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                ChangePaymentSettings(et_cardNumber.getText().toString(), et_holdersName.getText().toString(), et_dueDate.getText().toString(), et_secCode.getText().toString());
            }
        });
        ibtn_back.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { finish(); } });

        txt_message.setVisibility(View.GONE);
    }

    private void ChangePaymentSettings(String cardNumber, String holdersName, String dueDate, String secCode)
    {
        finish();
    }
}