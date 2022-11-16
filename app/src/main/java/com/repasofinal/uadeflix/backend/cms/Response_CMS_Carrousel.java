package com.repasofinal.uadeflix.backend.cms;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.repasofinal.uadeflix.logic.Catalog;
import com.repasofinal.uadeflix.logic.Movie;

import java.util.ArrayList;
import java.util.List;

public class Response_CMS_Carrousel {
    @SerializedName("title")
    private String title;
    @SerializedName("contents")
    private Response_CMS_Contenidos[] contenidos;

    public String getTitle() { return title; }
    public Response_CMS_Contenidos[] getContenidos() { return contenidos; }

    public Catalog ToCatalog()
    {
        List<Movie> movies = new ArrayList<Movie>();
        for (Response_CMS_Contenidos content: contenidos) { movies.add(content.ToMovie()); }
        return new Catalog(title, movies);
    }
}
