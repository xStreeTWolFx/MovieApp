package com.example.pelinabeer.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pelinabeer.database.Database;
import com.example.pelinabeer.database.dao.MovieDao;
import com.example.pelinabeer.models.Movie;
import com.example.pelinabeer.network.dto.MovieDto;
import com.example.pelinabeer.network.mapper.MovieMapper;
import com.example.pelinabeer.network.response.MovieResponse;
import com.example.pelinabeer.network.service.MovieService;
import com.example.pelinabeer.utilities.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private MovieDao movieDao;
    private MovieService service;
    private MutableLiveData<List<Movie>> movies;

    public MovieRepository(Context context) {
        Database db = Database.getDatabase(context);
        movieDao = db.movieDao();
        service = new MovieService();
        movies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(Context context) {
        if (NetworkUtil.isConnected(context)) {
            List<Movie> movieResponse = transform(service.getMovies());
            movies.postValue(movieResponse);
        } else {
            movies.setValue(movieDao.getAllMovies().getValue());
        }
        return movies;
    }

    public void insert(Movie movie) {
        Database.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }

    public void insert(Movie... movies) {
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

    private List<Movie> transform(LiveData<MovieResponse> movieResponse) {
        List<Movie> movies = new ArrayList<>();

        if (movieResponse != null) {
            MovieResponse responseValue = movieResponse.getValue();
            if (responseValue != null) {
                for (MovieDto movieDto : responseValue.getResult()) {
                    movies.add(MovieMapper.dtoToObject(movieDto));
                }
                return movies;
            } else {
                return movies;
            }
        } else {
            return movies;
        }
    }

}
