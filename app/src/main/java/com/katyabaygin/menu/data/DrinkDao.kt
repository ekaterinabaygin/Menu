package com.katyabaygin.menu.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.katyabaygin.menu.domain.Drink

@Dao
interface DrinkDao {
    @Insert
    fun insert(drink: Drink)

    @Delete
    fun delete(drink: Drink)

    @Update
    fun updateDrink(drink: Drink)

    @Query("SELECT * FROM drinks WHERE idDrink = :id")
    fun getDrinkById(id: String): Drink

    @Query("SELECT * FROM drinks")
    fun getAllDrinksLiveData(): LiveData<List<Drink>>
}
