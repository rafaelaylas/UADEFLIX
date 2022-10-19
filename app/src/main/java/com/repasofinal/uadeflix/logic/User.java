package com.repasofinal.uadeflix.logic;

import android.util.Log;

import com.repasofinal.uadeflix.enums.PLAN;
import com.repasofinal.uadeflix.support.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class User {
    private String token;
    private String refreshToken;
    private Date exp;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Card paymentInfo;
    private PLAN[] plan;

    public User(String[] tokens) {
        this.token = tokens[0];
        this.refreshToken = tokens[1];

        String[] decodedJWT = Helper.DecodeJWT(token);
        try {
            JSONObject obj = new JSONObject(decodedJWT[1]);
            this.name = obj.getString("nombre");
            this.email = obj.getString("email");
            this.exp = new Date((long)obj.getInt("exp")*1000);
        }
        catch (JSONException e) { e.printStackTrace(); }
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String name, String lastName, String phone, String email, String password, Card paymentInfo, PLAN[] plan) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.plan = plan;
    }

    public String getToken() { return token; }
    public String getRefreshToken() { return refreshToken; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Card getPaymentInfo() { return paymentInfo; }
    public PLAN[] getPlan() { return plan; }

    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public Boolean CheckExpiration() {
        if(exp.before(new Date())) { return true; }
        return false;
    }
}
