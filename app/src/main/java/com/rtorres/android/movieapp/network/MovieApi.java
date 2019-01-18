package com.rtorres.android.movieapp.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    String latest = "latest?access_key=305923ddf8db77f494fd38081be10532";

    @GET(latest)
    Call<MovieResponse> getMoviesInTeather();

}
