package com.katyabaygin.menu.domain

class AddDrinkUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(drink: Drink) {
        drinkRepository.addDrink(drink)
    }
}