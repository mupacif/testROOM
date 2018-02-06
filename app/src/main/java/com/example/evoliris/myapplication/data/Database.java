package com.example.evoliris.myapplication.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@android.arch.persistence.room.Database(entities = {Message.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static final String DB_NAME = "Database.db";
    private static Database instance;
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static Database create(final Context context) {
        return Room.databaseBuilder(
                context,
                Database.class,
                DB_NAME).build();
    }
    public abstract Dao getDao();

}
