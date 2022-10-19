package com.repasofinal.uadeflix.backend.cms;

import com.google.gson.annotations.SerializedName;
import com.repasofinal.uadeflix.logic.Movie;

public class Response_CMS_Contenidos {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("year")
    private Integer year;
    @SerializedName("duration")
    private Integer duration;
    @SerializedName("director")
    private String director;
    @SerializedName("cast")
    private String cast;
    @SerializedName("writer")
    private String writer;
    @SerializedName("genres")
    private Response_CMS_IdItem[] genres;
    @SerializedName("MaturityRating")
    private Response_CMS_IdItem MaturityRating;
    @SerializedName("urlImage")
    private String urlImage;
    @SerializedName("urlVideo")
    private String urlVideo;

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getYear() { return year; }
    public Integer getDuration() { return duration; }
    public String getDirector() { return director; }
    public String getCast() { return cast; }
    public String getWriter() { return writer; }
    public Response_CMS_IdItem[] getGenres() { return genres; }
    public Response_CMS_IdItem getMaturityRating() { return MaturityRating; }
    public String getUrlImage() { return urlImage; }
    public String getUrlVideo() { return urlVideo; }

    public Movie ToMovie(){
        String genresStr = "";
        for ( Response_CMS_IdItem item : genres ) { genresStr += item.getDescription() + ", "; }
        genresStr = genresStr.substring(0, genresStr.length() - 2);
        return new Movie(urlImage, title, description, year.toString(), duration.toString(), director, cast, writer, genresStr, MaturityRating.getDescription(), urlVideo);
    }
}
