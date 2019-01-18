package com.rtorres.android.movieapp.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rtorres.android.movieapp.R;
import com.rtorres.android.movieapp.adapter.MovieAdapter;
import com.rtorres.android.movieapp.database.MovieRepository;
import com.rtorres.android.movieapp.model.Movie;
import com.rtorres.android.movieapp.network.MovieApi;
import com.rtorres.android.movieapp.network.MovieClient;
import com.rtorres.android.movieapp.network.MovieResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        MovieAdapter adapter = new MovieAdapter(Objects.requireNonNull(getApplication()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
    }

    private void getMovies() {
        showProgress(true);
        MovieApi service = MovieClient.getRetrofitInstance().create(MovieApi.class);
        Call<MovieResponse> call = service.getMoviesInTeather();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (!response.isSuccessful()) {
                    showProgress(false);
                    Toast.makeText(MainActivity.this, R.string.request_error, Toast.LENGTH_LONG).show();
                } else {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        MovieRepository repository = new MovieRepository(getApplication());
                        for (Movie movie : movieResponse.getMovies()) {
                            repository.insert(movie);
                        }

                    } else {
                        showProgress(false);
                        Toast.makeText(MainActivity.this, R.string.request_error, Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, R.string.request_error, Toast.LENGTH_LONG).show();
                showProgress(false);
            }
        });
    }

    private void showProgress(boolean show) {
        if (progressBar != null)
            progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);

    }

    protected static class SaveMoviesAsyncTask extends AsyncTask<MovieResponse, Void, Void> {

        private SaveMoviesAsyncTask(MainActivity activity) {
        }

        @Override
        protected Void doInBackground(MovieResponse... movieResponses) {
            MovieResponse response = movieResponses[0];

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }
}
