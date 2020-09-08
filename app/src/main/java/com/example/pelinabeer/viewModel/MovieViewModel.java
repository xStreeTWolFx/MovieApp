package com.example.pelinabeer.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pelinabeer.repository.MovieRepository;
import com.example.pelinabeer.models.Movie;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<List<Movie>>getMovies(){
        return repository.getMovies(getApplication());
    }
}
