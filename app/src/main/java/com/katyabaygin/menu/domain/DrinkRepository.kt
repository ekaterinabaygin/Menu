package com.katyabaygin.menu.domain

import androidx.lifecycle.LiveData
import com.katyabaygin.menu.data.DrinkDao

interface DrinkRepository {
    suspend fun getDrinks(): LiveData<List<Drink>>
    suspend fun getDrinkById(id: String): Drink?
    suspend fun addDrink(drink: Drink)
    suspend fun updateDrink(drink: Drink)
    suspend fun deleteDrink(drink: Drink)
}

class DrinkRepositoryImpl(private val drinkDao: DrinkDao) : DrinkRepository {
    override suspend fun getDrinks(): LiveData<List<Drink>> {
        return drinkDao.getAllDrinksLiveData()
    }

    override suspend fun getDrinkById(id: String): Drink {
        return drinkDao.getDrinkById(id)
    }

    override suspend fun addDrink(drink: Drink) {
        drinkDao.insert(drink)
    }

    override suspend fun updateDrink(drink: Drink) {
        drinkDao.updateDrink(drink)
    }

    override suspend fun deleteDrink(drink: Drink) {
        drinkDao.delete(drink)
    }
}
