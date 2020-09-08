package com.example.pelina.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pelina.models.Movie;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<Movie> movie);

    @Query("DELETE FROM movies")
    void deleteAll();

    @Delete
    void deleteAll(Movie... movies);

    @Query("SELECT * from movies ORDER BY id DESC")
    List<Movie> getAllMovies();

}
