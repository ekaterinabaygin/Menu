package com.katyabaygin.menu;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DrinkDao {
    @Insert
    void insert(Drink drink);

    @Update
    void update(Drink drink);

    @Update
    void saveOrder(Drink drink);

    @Delete
    void delete(Drink drink);

    @Query("SELECT * FROM drinks")
    List<Drink> getAllDrinks();

    @Query("SELECT * FROM drinks WHERE idDrink = :id")
    Drink getDrinkById(int id);


    @Query("SELECT * FROM drinks")
    LiveData<List<Drink>> getAllDrinksLiveData();





}
