package com.repasofinal.uadeflix.logic;

import java.util.List;

public class Catalog {
    private String title;
    private List<Movie> movies;
    private int offset;

    public Catalog(String title, List<Movie> movies) {
        this.title = title;
        this.movies = movies;
        offset = 0;
    }

    public void addOffset() { if(offset < movies.size()) { offset++; } }
    public void subOffset() { if(offset > 0) { offset--; } }
    public int getOffset() { return offset; }
    public String getTitle() { return title; }
    public List<Movie> getMovies() { return movies; }
}
