package com.rtorres.android.movieapp.adapter;

import android.app.Application;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rtorres.android.movieapp.R;
import com.rtorres.android.movieapp.model.Movie;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Movie> movies;

    public MovieAdapter(Application application) {
        layoutInflater = LayoutInflater.from(application);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (movies != null) {
            Movie movie = movies.get(position);
            holder.tittleTexView.setText(movie.getTittle());
            holder.overViewTextView.setText(movie.getOverview());
            holder.ratingTextView.setText(String.format(Locale.US, "%.2f", movie.getVoteAverage()));
            holder.favoriteImageButton.setActivated(movie.getFavorite());
        }
    }

    void setMovies(List<Movie> movies) {
        movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (movies != null)
            return movies.size();
        else return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView tittleTexView;
        private TextView overViewTextView;
        private TextView ratingTextView;
        private ImageButton favoriteImageButton;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tittleTexView = itemView.findViewById(R.id.tittle_textView);
            overViewTextView = itemView.findViewById(R.id.overView_textView);
            ratingTextView = itemView.findViewById(R.id.rating_textView);
            favoriteImageButton = itemView.findViewById(R.id.favorite_imageButton);

        }
    }
}
