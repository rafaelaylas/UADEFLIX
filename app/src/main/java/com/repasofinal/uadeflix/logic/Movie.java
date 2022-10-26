package com.repasofinal.uadeflix.logic;

public class Movie {
    private int id;
    private String imageWideSrc;
    private String imagePosterSrc;
    private String title;
    private String description;
    private String year;
    private String duration;
    private String director;
    private String cast;
    private String writer;
    private String genre;
    private String rank;
    private String videoSrc;

    public Movie(int id, String imagePosterSrc, String imageWideSrc, String title, String description, String year, String duration, String director, String cast, String writer, String genre, String rank, String videoSrc) {
        this.id = id;
        this.imagePosterSrc = imagePosterSrc;
        this.imageWideSrc = imageWideSrc;
        this.title = title;
        this.description = description;
        this.year = year;
        this.duration = duration;
        this.director = director;
        this.cast = cast;
        this.writer = writer;
        this.genre = genre;
        this.rank = rank;
        this.videoSrc = videoSrc;
    }

    public int getId() { return id; }
    public String getImagePosterSrc() { return imagePosterSrc; }
    public String getImageWideSrc() { return imageWideSrc; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getYear() { return year; }
    public String getDuration() { return duration; }
    public String getDirector() { return director; }
    public String getCast() { return cast; }
    public String getWriter() { return writer; }
    public String getGenre() { return genre; }
    public String getRank() { return rank; }
    public String getVideoSrc() { return videoSrc; }
}
