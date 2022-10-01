package com.repasofinal.uadeflix.logic;

import com.repasofinal.uadeflix.enums.PLAN;

public class User {
    private String token;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Card paymentInfo;
    private PLAN plan;

    public User(String name, String lastName, String phone, String email, Card paymentInfo, PLAN plan) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.plan = plan;
    }

    public User(String token, String name, String lastName, String phone, String email, Card paymentInfo, PLAN plan) {
        this.token = token;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.plan = plan;
    }

    public String getToken() { return token; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public Card getPaymentInfo() { return paymentInfo; }
    public PLAN getPlan() { return plan; }

    public void setToken(String token) { this.token = token; }
}
