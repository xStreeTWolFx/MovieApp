package com.example.pelina.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pelina.database.Database;
import com.example.pelina.database.dao.MovieDao;
import com.example.pelina.models.Movie;
import com.example.pelina.network.dto.MovieDto;
import com.example.pelina.network.mapper.MovieMapper;
import com.example.pelina.network.response.MovieResponse;
import com.example.pelina.network.service.MovieService;
import com.example.pelina.utilities.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    Database db;
    private MovieDao movieDao;
    private MovieService service;
    private MutableLiveData<List<Movie>> moviesLive;

    public MovieRepository(Context context) {
        db = Database.getDatabase(context);
        movieDao = db.movieDao();
        service = new MovieService();
        moviesLive = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(Context context) {
        if (NetworkUtil.isConnected(context)) {
            callService();
            moviesLive.setValue(new ArrayList<>());
        } else {
            getMovies();
        }
        return moviesLive;
    }

    private void callService() {
        service.getMovies(new MovieService.OnGet() {
            @Override
            public void onResponse(MovieResponse response) {
                List<Movie> movies = new ArrayList<>();

                if (response != null && response.getResult() != null) {
                    for (MovieDto movieDto : response.getResult()) {
                        movies.add(MovieMapper.dtoToObject(movieDto));
                    }
                    insert(new ArrayList<>(movies));
                    moviesLive.setValue(movies);
                }
            }

            @Override
            public void onFailure(Object error) {
                moviesLive.setValue(new ArrayList<>());

            }
        });
    }

    public void insert(Movie movie) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }

    public void insert(ArrayList<Movie> movies) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insertAll(movies);
        });
    }

    public void deleteAll(Movie... movies) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.deleteAll(movies);
        });
    }

    public void deleteAll() {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.deleteAll();
        });
    }

    public void getMovies() {
        Database.databaseWriteExecutor.execute(() -> {
            moviesLive.postValue(movieDao.getAllMovies());
        });
    }
}
