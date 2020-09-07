package com.example.pelinabeer.models;

import java.util.Date;

public class Movie {

    private String title;
    private String overview;
    private Date releaseDate;
    private String poster;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie(String title, String overview, Date releaseDate, String poster) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.poster = poster;
    }
}
