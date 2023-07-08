package com.katyabaygin.menu.data

import com.katyabaygin.menu.domain.Drink
import retrofit2.http.GET

interface ApiService {
    @GET("api/json/v1/1/filter.php?a=Alcoholic")
    suspend fun loadDrinks(): List<Drink>
}




