package com.example.pelina.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pelina.R;
import com.example.pelina.adapters.MovieRecycleAdapter;
import com.example.pelina.models.Movie;
import com.example.pelina.viewModel.MovieViewModel;

import java.util.List;


public class MovieListFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieRecycleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MovieViewModel viewModel;

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        adapter = new MovieRecycleAdapter();
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView = view.findViewById(R.id.recycle_view_movie);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovieList(movies);
            }
        });
        return view;
    }
}