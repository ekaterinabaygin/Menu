package com.katyabaygin.menu.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DrinkResponse(
    @SerializedName("drinks")
    @Expose
    private var drinkList: List<Drink>
) {
    fun getDrinks(): List<Drink> {
        return drinkList
    }

    @JvmName("setDrinksList")
    fun setDrinks(drinks: List<Drink>) {
        drinkList = drinks
    }

    override fun toString(): String {
        return "DrinkResponse{" +
                "drinks=" + drinkList +
                '}'
    }
}
