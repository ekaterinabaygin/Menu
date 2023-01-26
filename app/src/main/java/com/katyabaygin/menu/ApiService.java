package com.katyabaygin.menu;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/json/v1/1/filter.php?a=Alcoholic")
    Single<DrinkResponse> loadDrinks();
}
