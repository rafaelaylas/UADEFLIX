package com.repasofinal.uadeflix.logic;

import java.util.List;

public class Catalog {
    private String title;
    private List<Movie> movies;

    public Catalog(String title, List<Movie> movies) {
        this.title = title;
        this.movies = movies;
    }

    public String getTitle() { return title; }
    public List<Movie> getMovies() { return movies; }
}
