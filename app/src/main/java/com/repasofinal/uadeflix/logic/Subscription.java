package com.repasofinal.uadeflix.logic;

public class Subscription {
    private int id;
    private String title;
    private String description;
    private String price;

    public Subscription(int id, String title, String description, String price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public int GetId() { return id; }
    public String GetTitle() { return title; }
    public String GetDescription() { return description; }
    public String GetPrice() { return price; }
}
