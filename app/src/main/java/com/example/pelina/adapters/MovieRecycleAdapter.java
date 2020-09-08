package com.example.pelina.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelina.R;
import com.example.pelina.models.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieRecycleAdapter extends RecyclerView.Adapter<MovieRecycleAdapter.ViewHolder> {
    List<Movie> movieList = new ArrayList<>();

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList ==null?new ArrayList<>():movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        String path = movie.getPosterPath();
        String prefix = "https://image.tmdb.org/t/p/w500";
        Picasso.get().load(prefix + path).resize(400, 400).centerInside()
                .error(R.drawable.ic_cinema).into(holder.movieImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        ProgressBar progressBar;

        ViewHolder(View view) {
            super(view);
            movieImage = view.findViewById(R.id.image_movie);
            progressBar = view.findViewById(R.id.progress_image);
        }
    }
}
