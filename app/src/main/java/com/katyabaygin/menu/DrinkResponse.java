package com.katyabaygin.menu;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinkResponse {
    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks;


    public DrinkResponse(List<Drink> drinks) {
        super();
        this.drinks = drinks;
    }
@NonNull
    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    @NonNull
    public String toString() {
        return "DrinkResponse{" +
                "drinks=" + drinks +
                '}';
    }
}
