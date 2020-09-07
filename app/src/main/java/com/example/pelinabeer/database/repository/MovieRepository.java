package com.example.pelinabeer.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.pelinabeer.database.Database;
import com.example.pelinabeer.database.dao.MovieDao;
import com.example.pelinabeer.models.Movie;

import java.util.List;

public class MovieRepository {


    private MovieDao movieDao;
    private LiveData<List<Movie>> movies;

    MovieRepository(Application application) {
        Database db = Database.getDatabase(application);
        movieDao = db.movieDao();
        movies = movieDao.getAllMovies();
    }

    LiveData<List<Movie>> getMovies() {
        return movies;
    }

    void insert(Movie movie) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }

    void insert(Movie... movie) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insertAll(movie);
        });
    }
}
