package com.katyabaygin.menu.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "drinks")
data class Drink(
    @PrimaryKey
    @SerializedName("idDrink")
    var idDrink: String,

    @SerializedName("name")

    var name: String,

    @SerializedName("strDrink")
    var strDrink: String,

    @SerializedName("strDrinkThumb")

    var strDrinkThumb: String,

    @ColumnInfo(name = "drink_note")
    var drinkNote: String
) : Serializable {
    var orderedDrinksCount: Int? = null

    override fun toString(): String {
        return "Drink{" +
                "idDrink='$idDrink'" +
                "name='$name'" +
                ", strDrink='$strDrink'" +
                ", strDrinkThumb='$strDrinkThumb'" +
                ", drinkNote='$drinkNote'" +
                '}'
    }
}
