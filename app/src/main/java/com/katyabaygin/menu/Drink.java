//package com.katyabaygin.menu;
//
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//import com.google.gson.annotations.SerializedName;
//
//import java.io.Serializable;
//
//@Entity(tableName = "drinks")
//public class Drink implements Serializable {
//
//    @PrimaryKey
//    @NonNull
//    @SerializedName("idDrink")
//    private int idDrink;
//    @SerializedName("strDrink")
//    @NonNull
//    private String strDrink;
//
//   @SerializedName("strDrinkThumb")
//   @NonNull
//    private String strDrinkThumb;
//    @SerializedName("drinkNote")
//    @NonNull
//    private String drinkNote;
//
//    public Drink(int idDrink, String strDrink, String strDrinkThumb, String drinkNote) {
//        this.idDrink = idDrink;
//        this.strDrink = strDrink;
//        this.strDrinkThumb = strDrinkThumb;
//        this.drinkNote = drinkNote;
//    }
//
//    public int getIdDrink() {
//        return idDrink;
//    }
//
//    public void setIdDrink(int idDrink) {
//        this.idDrink = idDrink;
//    }
//
//    public String getStrDrink() {
//        return strDrink;
//    }
//
//    public void setStrDrink(String strDrink) {
//        this.strDrink = strDrink;
//    }
//
//    public String getStrDrinkThumb() {
//        return strDrinkThumb;
//    }
//
//    public void setStrDrinkThumb(String strDrinkThumb) {
//        this.strDrinkThumb = strDrinkThumb;
//    }
//
//    public String getDrinkNote() {
//        return drinkNote;
//    }
//
//    public void setDrinkNote(String drinkNote) {
//        this.drinkNote = drinkNote;
//    }
//
//
//    @NonNull
//    @Override
//    public String toString() {
//        return "Drink{" +
//                "idDrink=" + idDrink +
//                ", strDrink='" + strDrink + '\'' +
//                ", strDrinkThumb='" + strDrinkThumb + '\'' +
//                ", drinkNote='" + drinkNote + '\'' +
//                '}';
//    }
//}

package com.katyabaygin.menu;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "drinks")
public class Drink implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("idDrink")
    private String idDrink;

    @SerializedName("strDrink")
    @NonNull
    private String strDrink;

    @SerializedName("strDrinkThumb")
    @NonNull
    private String strDrinkThumb;

    @ColumnInfo(name = "drink_note")
    @NonNull
    private String drinkNote;

    private int orderedDrinksCount;

    public int getOrderedDrinksCount() {
        return orderedDrinksCount;
    }

    public void setOrderedDrinksCount(int count) {
        this.orderedDrinksCount = count;
    }

    public Drink(String idDrink, String strDrink, String strDrinkThumb, String drinkNote) {
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strDrinkThumb = strDrinkThumb;
        this.drinkNote = drinkNote;
    }

    @NonNull
    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(@NonNull String idDrink) {
        this.idDrink = idDrink;
    }

    @NonNull
    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(@NonNull String strDrink) {
        this.strDrink = strDrink;
    }

    @NonNull
    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(@NonNull String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    @NonNull
    public String getDrinkNote() {
        return drinkNote;
    }

    public void setDrinkNote(@NonNull String drinkNote) {
        this.drinkNote = drinkNote;
    }

    @NonNull
    @Override
    public String toString() {
        return "Drink{" +
                "idDrink='" + idDrink + '\'' +
                ", strDrink='" + strDrink + '\'' +
                ", strDrinkThumb='" + strDrinkThumb + '\'' +
                ", drinkNote='" + drinkNote + '\'' +
                '}';
    }
}
