package com.repasofinal.uadeflix.logic;

import com.repasofinal.uadeflix.support.StaticData;

import java.util.List;

public class Manager {
    private User currentUser;
    private Movie currentMovie;
    private List<Catalog> catalogs;

    public Boolean SignIn(String email, String password) { return true; }
    public Boolean SignUp(User newUser) { return true; }
    public Boolean UpdatePaymentInfo(Card newCard) { return true; }
    public Boolean UpdatePassword(String newPassword) { return true; }
    public Boolean UpdateMovies()
    {
        catalogs = StaticData.GetCatalogs();
        return true;
    }

    public void SetCurrentMovie(Movie movie){ currentMovie = movie; }
    public Movie GetCurrentMovie() { return currentMovie; }
    public List<Catalog> GetCatalogs() { return catalogs; }
}
