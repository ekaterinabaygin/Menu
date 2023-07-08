package com.katyabaygin.menu.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.katyabaygin.menu.domain.Drink

@Database(entities = [Drink::class], version = 1, exportSchema = false)
abstract class DrinkDataBase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object {
        private const val DB_NAME = "my_drinks.db"
        private var instance: DrinkDataBase? = null

        fun getInstance(application: Application): DrinkDataBase {
            if (instance == null) {
                synchronized(DrinkDataBase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            application.applicationContext,
                            DrinkDataBase::class.java, DB_NAME
                        ).build()
                    }
                }
            }
            return instance as DrinkDataBase
        }
    }
}
