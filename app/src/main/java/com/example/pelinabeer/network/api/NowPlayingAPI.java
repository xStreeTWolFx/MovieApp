package com.example.pelinabeer.network.api;

import com.example.pelinabeer.network.response.NowPlayingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NowPlayingAPI {
    String NOW_PLAYING = "movie/now_playing";

    @GET(NOW_PLAYING)
    Call<NowPlayingResponse> getNowPlaying();
}
