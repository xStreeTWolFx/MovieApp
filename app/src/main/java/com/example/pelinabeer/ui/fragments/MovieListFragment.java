package com.example.pelinabeer.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pelinabeer.R;
import com.example.pelinabeer.adapters.MovieRecycleAdapter;
import com.example.pelinabeer.models.Movie;

import java.util.Arrays;
import java.util.Date;


public class MovieListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieRecycleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Movie movie = new Movie("", "", new Date(), "https://freesvg.org/img/1547626868.png");
    Movie movie2 = new Movie("", "", new Date(), "https://image.tmdb.org/t/p/w500/uOw5JD8IlD546feZ6oxbIjvN66P.jpg");

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance() {

        return new MovieListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        adapter = new MovieRecycleAdapter(Arrays.asList(movie, movie2, movie, movie2, movie2, movie, movie, movie2));
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView = view.findViewById(R.id.recycle_view_movie);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}