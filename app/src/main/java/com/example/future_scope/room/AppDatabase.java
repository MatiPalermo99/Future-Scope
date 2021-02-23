package com.example.future_scope.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.future_scope.model.Review;

@Database(entities = {Review.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase DB;
    private static String DATABASE_NAME="database";

    public static AppDatabase getInstance(Context ctx) {
        if(DB==null) {
            DB = Room.databaseBuilder(ctx,
                    AppDatabase.class,
                    DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return DB;
    }

    public abstract ReviewDAORoom reviewDao();
}