package com.rtorres.android.movieapp.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.rtorres.android.movieapp.model.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        movies = movieRepository.getMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void inset(Movie movie){
        movieRepository.insert(movie);
    }
}
