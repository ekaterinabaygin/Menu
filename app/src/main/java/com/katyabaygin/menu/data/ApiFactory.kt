package com.katyabaygin.menu.data

import android.app.Application
import com.katyabaygin.menu.domain.DrinkRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://www.thecocktaildb.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    private var database: DrinkDataBase? = null

    fun getDrinkRepository(application: Application): DrinkRepository {
        val drinkDao = getDrinkDao(application)
        return DrinkRepositoryImpl(drinkDao, apiService)
    }

    private fun getDrinkDao(application: Application): DrinkDao {
        if (database == null) {
            database = DrinkDataBase.getInstance(application)
        }
        return database!!.drinkDao()
    }
}
