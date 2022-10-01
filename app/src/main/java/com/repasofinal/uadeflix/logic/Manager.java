package com.repasofinal.uadeflix.logic;

import java.util.List;

public class Manager {
    private User currentUser;
    private List<Catalog> catalogs;

    public Boolean SignIn(String email, String password) { return true; }
    public Boolean SignUp(User newUser) { return true; }
    public Boolean UpdatePaymentInfo(Card newCard) { return true; }
    public Boolean UpdatePassword(String newPassword) { return true; }
    public Boolean UpdateMovies() { return true; }
}
