package com.rtorres.android.movieapp.database;

import android.app.Application;
import android.os.AsyncTask;

import com.rtorres.android.movieapp.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MovieRepository {

    private MovieDao movieDao;
    private LiveData<List<Movie>> movies;

    public MovieRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.movieDao = db.movieDao();
        this.movies = movieDao.getAllMovies();
    }

    LiveData<List<Movie>> getMovies() {
        return movies;
    }


    public void insert(Movie movie) {
        new insertAsyncTask(movieDao).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
