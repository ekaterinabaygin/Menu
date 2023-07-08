package com.katyabaygin.menu.domain

import androidx.lifecycle.LiveData

class GetDrinksUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(): LiveData<List<Drink>> {
        return drinkRepository.getDrinks()
    }
}
