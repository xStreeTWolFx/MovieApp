package com.example.pelina.network.service;

import androidx.annotation.NonNull;

import com.example.pelina.network.ClientUsage;
import com.example.pelina.network.apiService.MovieApi;
import com.example.pelina.network.response.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieService {


    public void getMovies(OnGet callback) {
        MovieApi service = ClientUsage.getRetrofitInstance().create(MovieApi.class);
        Call<MovieResponse> call = service.getMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                callback.onResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                callback.onFailure(null);
            }
        });
    }

    public interface OnGet {
        void onResponse(MovieResponse response);

        void onFailure(Object error);
    }
}
