package com.example.pelina.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pelina.database.dao.MovieDao;
import com.example.pelina.models.Movie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static Database INSTANCE;


    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "movie_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();

}
