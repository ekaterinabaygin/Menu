package com.katyabaygin.menu.data

import androidx.lifecycle.LiveData
import com.katyabaygin.menu.domain.Drink
import com.katyabaygin.menu.domain.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DrinkRepositoryImpl(
    private val drinkDao: DrinkDao,
    private val apiService: ApiService
) : DrinkRepository {

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

    override suspend fun getDrinks(): LiveData<List<Drink>> {
        val localDrinks = drinkDao.getAllDrinksLiveData().value
        return if (!localDrinks.isNullOrEmpty()) {
            drinkDao.getAllDrinksLiveData()
        } else {
            val remoteDrinks = fetchRemoteDrinks()
            for (drink in remoteDrinks) {
                drinkDao.insert(drink)
            }
            drinkDao.getAllDrinksLiveData()
        }
    }

    private suspend fun fetchRemoteDrinks(): List<Drink> {
        return withContext(Dispatchers.IO) {
            try {
                val drinksResponse = apiService.loadDrinks()
                drinksResponse // Modify this line based on the structure of the API response
            } catch (e: Exception) {
                emptyList()
            }
        }
    }


    fun saveDrink(drink: Drink) {
        drinkDao.insert(drink)
    }
}
