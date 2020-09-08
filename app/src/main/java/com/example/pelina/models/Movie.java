package com.example.pelina.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    private String title;
    private String overview;
    private Long releaseDate;
    private String posterPath;
    private Double voteAverage;

    public Movie(int id, String title, String overview, Long releaseDate, String posterPath, Double voteAverage) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = cleanPath(posterPath);
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

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    private String cleanPath(String posterPath) {
        int pos =  posterPath.lastIndexOf("/");
        String result = posterPath.substring(pos+1);

        return result;
    }

}
