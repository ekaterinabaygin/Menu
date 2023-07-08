package com.katyabaygin.menu.domain

class DeleteDrinkUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(drink: Drink) {
        drinkRepository.deleteDrink(drink)
    }
}