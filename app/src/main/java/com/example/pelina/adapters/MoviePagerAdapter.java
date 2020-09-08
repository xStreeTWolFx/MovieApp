package com.example.pelina.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pelina.ui.fragments.FavoriteMovieFragment;
import com.example.pelina.ui.fragments.MovieListFragment;

public class MoviePagerAdapter extends FragmentStateAdapter {

    public MoviePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MoviePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MoviePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MovieListFragment();
            case 1:
                return new FavoriteMovieFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
