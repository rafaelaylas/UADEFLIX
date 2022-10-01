package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.repasofinal.uadeflix.R;

public class Catalog extends AppCompatActivity {

    private ImageButton ibtn_menu;
    private ConstraintLayout clay_menuContainer;
    private ConstraintLayout clay_menu;
    private ConstraintLayout clay_btnPlan;
    private ConstraintLayout clay_btnPayment;
    private ConstraintLayout clay_btnPassword;
    private ConstraintLayout clay_btnSignOut;
    private ConstraintLayout clay_closeMenu;

    private ListView lview_catalog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        ibtn_menu = (ImageButton) findViewById(R.id.catalog_ibtn_menu);
        clay_menuContainer = (ConstraintLayout) findViewById(R.id.catalog_clay_menuContainer);
        clay_menu = (ConstraintLayout) findViewById(R.id.catalog_clay_menu);
        clay_btnPlan = (ConstraintLayout) findViewById(R.id.catalog_clay_btnPlan);
        clay_btnPayment = (ConstraintLayout) findViewById(R.id.catalog_clay_btnPayment);
        clay_btnPassword = (ConstraintLayout) findViewById(R.id.catalog_clay_btnPassword);
        clay_btnSignOut = (ConstraintLayout) findViewById(R.id.catalog_clay_btnSignOut);
        clay_closeMenu = (ConstraintLayout) findViewById(R.id.catalog_clay_closeMenu);

        lview_catalog = (ListView) findViewById(R.id.catalog_lview_catalog);

        ibtn_menu.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { OpenMenu(); } });
        clay_btnPlan.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePlan(); } });
        clay_btnPayment.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePaymentSettings(); } });
        clay_btnPassword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePassword(); } });
        clay_btnSignOut.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { SignOut(); } });
        clay_closeMenu.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { CloseMenu(); } });

        clay_menuContainer.setVisibility(View.GONE);
    }

    private void OpenMenu() { clay_menuContainer.setVisibility(View.VISIBLE); }
    private void CloseMenu() { clay_menuContainer.setVisibility(View.GONE); }
    private void ChangePlan() {}
    private void ChangePaymentSettings() { startActivity(new Intent(Catalog.this, PaymentInfo.class)); }
    private void ChangePassword() { startActivity(new Intent(Catalog.this, ChangePassword.class)); }
    private void SignOut() { finish(); }
}