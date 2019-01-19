package com.rtorres.android.movieapp.database;

import com.rtorres.android.movieapp.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie... movie);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("DELETE FROM movie")
    void deleteAll();

    @Query("SELECT * FROM movie m ORDER BY m.tittle ASC")
    LiveData<List<Movie>> getAllMovies();

}
