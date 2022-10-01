package com.repasofinal.uadeflix.logic;

public class Card {
    private String cardNumber;
    private String holdersName;
    private String dueDate;
    private String secCode;

    public Card(String cardNumber, String holdersName, String dueDate, String secCode) {
        this.cardNumber = cardNumber;
        this.holdersName = holdersName;
        this.dueDate = dueDate;
        this.secCode = secCode;
    }

    public String getCardNumber() { return cardNumber; }
    public String getHoldersName() { return holdersName; }
    public String getDueDate() { return dueDate; }
    public String getSecCode() { return secCode; }
}
