package com.katyabaygin.menu;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


@Database(entities = {Drink.class}, version = 1, exportSchema = false)
public abstract class DrinkDataBase extends RoomDatabase {
    public abstract DrinkDao drinkDao();

    private static final String DB_NAME = "my_drinks.db";
    private static DrinkDataBase instance = null;

    public static DrinkDataBase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    DrinkDataBase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return Room.databaseBuilder(configuration.context.getApplicationContext(), DrinkDataBase.class, DB_NAME).build().getOpenHelper();
    }}

