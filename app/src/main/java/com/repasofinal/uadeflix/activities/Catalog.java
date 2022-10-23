package com.repasofinal.uadeflix.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.repasofinal.uadeflix.MainActivity;
import com.repasofinal.uadeflix.R;
import com.repasofinal.uadeflix.logic.Manager;
import com.repasofinal.uadeflix.support.ActionV;
import com.repasofinal.uadeflix.support.CatalogListAdapter;
import com.repasofinal.uadeflix.support.Helper;

import java.util.ArrayList;

public class Catalog extends AppCompatActivity {

    private ImageButton ibtn_menu;
    private ConstraintLayout clay_menuContainer;
    private ConstraintLayout clay_menu;
    private ConstraintLayout clay_btnPlan;
    private ConstraintLayout clay_btnPayment;
    private ConstraintLayout clay_btnPassword;
    private ConstraintLayout clay_btnSignOut;
    private ConstraintLayout clay_closeMenu;
    private ConstraintLayout clay_loadingScreen;
    private TextView txt_user;
    private TextView tv_loading;
    private ImageButton btn_loading;

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
        clay_loadingScreen = (ConstraintLayout) findViewById(R.id.catalog_clay_loadingScreen);
        txt_user = (TextView) findViewById(R.id.catalog_txt_user);
        tv_loading = (TextView) findViewById(R.id.catalog_tv_loading);
        btn_loading = (ImageButton) findViewById(R.id.catalog_btn_refresh);

        lview_catalog = (ListView) findViewById(R.id.catalog_lview_catalog);

        txt_user.setText(MainActivity.manager.GetCurrentUser().getName());

        ibtn_menu.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { OpenMenu(); } });
        clay_btnPlan.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePlan(); } });
        clay_btnPayment.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePaymentSettings(); } });
        clay_btnPassword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { ChangePassword(); } });
        clay_btnSignOut.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { SignOut(); } });
        clay_closeMenu.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { CloseMenu(); } });
        btn_loading.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { Refresh(); } });

        clay_menuContainer.setVisibility(View.GONE);
        clay_loadingScreen.setVisibility(View.GONE);
        btn_loading.setVisibility(View.GONE);

        Refresh();
    }

    private void Refresh() {
        tv_loading.setText("Loading...");
        clay_loadingScreen.setVisibility(View.VISIBLE);
        MainActivity.manager.UpdateMovies(
                new ActionV() { @Override public void Invoke() {
                    btn_loading.setVisibility(View.GONE);
                    UpdateCatalog();
                    clay_loadingScreen.setVisibility(View.GONE);
                } },
                new ActionV() { @Override public void Invoke() {
                    tv_loading.setText("Something went wrong with the server!");
                    btn_loading.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() { public void run() { clay_loadingScreen.setVisibility(View.GONE); } }, 3000);
                } },
                new ActionV() { @Override public void Invoke()
                {
                    tv_loading.setText("Something went wrong! Please check your internet conection");
                    btn_loading.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() { public void run() { clay_loadingScreen.setVisibility(View.GONE); } }, 3000);
                } }
        );
    }
    private void UpdateCatalog() {
        ArrayList<com.repasofinal.uadeflix.logic.Catalog> items = Helper.listToArrayList(MainActivity.manager.GetCatalogs());
        CatalogListAdapter adapter = new CatalogListAdapter(this, R.layout.list_view_catalog, items);
        lview_catalog.setAdapter(adapter);
    }
    private void OpenMenu() { clay_menuContainer.setVisibility(View.VISIBLE); }
    private void CloseMenu() { clay_menuContainer.setVisibility(View.GONE); }
    private void ChangePlan() {}
    private void ChangePaymentSettings() { startActivity(new Intent(Catalog.this, PaymentInfo.class)); }
    private void ChangePassword() { startActivity(new Intent(Catalog.this, ChangePassword.class)); }
    private void SignOut() {
        clay_loadingScreen.setVisibility(View.VISIBLE);
        MainActivity.manager.SignOut(
                new ActionV() { @Override public void Invoke() { finish(); } },
                new ActionV() { @Override public void Invoke() { clay_loadingScreen.setVisibility(View.GONE); } },
                new ActionV() { @Override public void Invoke() { clay_loadingScreen.setVisibility(View.GONE); } }
        ); }
}