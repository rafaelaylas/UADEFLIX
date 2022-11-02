package com.repasofinal.uadeflix.logic;

import android.util.Log;

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
    private Subscription[] suscriptions;

    public User(String[] tokens) {
        this.token = tokens[0];
        setRefreshToken(tokens[1]);

        String[] decodedJWT = Helper.DecodeJWT(token);
        try {
            JSONObject obj = new JSONObject(decodedJWT[1]);
            this.name = obj.getString("nombre");
            this.email = obj.getString("email");
            setExpirationDate(obj.getInt("exp"));
        }
        catch (JSONException e) { e.printStackTrace(); }
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(String name, String lastName, String phone, String email, String password, Card paymentInfo, Subscription[] plan) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.suscriptions = plan;
    }

    public String getToken() { return token; }
    public String getRefreshToken() { return refreshToken; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Card getPaymentInfo() { return paymentInfo; }
    public Subscription[] getSuscriptions() { return suscriptions; }

    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public void setExpirationDate(int date) { this.exp = new Date((long)(date-600)*1000); }
    public Boolean CheckExpiration()
    {
        Log.d("Response","Exp Date: " + exp + "/ now: " + new Date());
        if(exp.before(new Date())) { return false; }
        return true;
    }
}
