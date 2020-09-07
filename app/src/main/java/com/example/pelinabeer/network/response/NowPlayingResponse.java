package com.example.pelinabeer.network.response;

import com.example.pelinabeer.models.Movie;

import java.util.List;

public class NowPlayingResponse {

    private List<Movie> result;

    public NowPlayingResponse(List<Movie> result) {
        this.result = result;
    }

    public List<Movie> getResult() {
        return result;
    }

    public void setResult(List<Movie> result) {
        this.result = result;
    }
}
