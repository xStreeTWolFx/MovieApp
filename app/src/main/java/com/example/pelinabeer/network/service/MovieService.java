package com.example.pelinabeer.network.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pelinabeer.network.ClientUsage;
import com.example.pelinabeer.network.apiService.MovieApi;
import com.example.pelinabeer.network.response.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService {

    private MutableLiveData<MovieResponse> movies = new MutableLiveData<>();

    public LiveData<MovieResponse> getMovies() {
        MovieApi service = ClientUsage.getRetrofitInstance().create(MovieApi.class);
        Call<MovieResponse> call = service.getMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                movies.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                movies.postValue(null);
            }
        });
        return movies;
    }
}
