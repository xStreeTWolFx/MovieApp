package com.rtorres.android.movieapp.network;

import com.google.gson.annotations.SerializedName;
import com.rtorres.android.movieapp.model.Movie;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

}
