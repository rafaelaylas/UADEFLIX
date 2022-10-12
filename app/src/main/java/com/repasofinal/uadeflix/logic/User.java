package com.repasofinal.uadeflix.logic;

import android.util.Log;

import com.repasofinal.uadeflix.enums.PLAN;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User {
    private String token;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Card paymentInfo;
    private PLAN plan;

    public User(String token) {
        this.token = token;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;

        Log.d("register", this.password);
    }
    public User(String name, String lastName, String phone, String email, String password, Card paymentInfo, PLAN plan) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.paymentInfo = paymentInfo;
        this.plan = plan;
        Log.d("register", this.password);
    }

    public String getToken() { return token; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Card getPaymentInfo() { return paymentInfo; }
    public PLAN getPlan() { return plan; }

    private String setPassword(String password) { return get_SHA_256_SecurePassword(password,"MySecretPass"); }
    public void setToken(String token) { this.token = token; }
    private String get_SHA_256_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) { sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)); }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return generatedPassword;
    }
}
