package com.katyabaygin.menu;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiFactory {

    private static final String BASE_URL = "https://www.thecocktaildb.com/";

  private static final  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();


    public static final ApiService apiService = retrofit.create(ApiService.class);

}
