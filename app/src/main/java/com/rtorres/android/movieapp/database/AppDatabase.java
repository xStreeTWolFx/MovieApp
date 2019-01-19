package com.rtorres.android.movieapp.database;

import android.content.Context;
import android.os.AsyncTask;

import com.rtorres.android.movieapp.model.Movie;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class, "movie_db")
                        .addCallback(dataBaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }


    private AppDatabase() {
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
    private static RoomDatabase.Callback dataBaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private MovieDao movieDao;

        PopulateDbAsync(AppDatabase db) {
            movieDao = db.movieDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            movieDao.deleteAll();
            Movie movie = new Movie("Hello","",2.3D,true);
            movieDao.insert(movie);
            movie = new Movie("World","",3.4D,false);
            movieDao.insert(movie);
            return null;
        }
    }
}
