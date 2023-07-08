package com.katyabaygin.menu.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.katyabaygin.menu.R
import com.katyabaygin.menu.domain.Drink

class MyOrdersActivity : AppCompatActivity() {

    private lateinit var textViewOrderedDrink: TextView
    private lateinit var imageViewDrinkOrder: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        textViewOrderedDrink = findViewById(R.id.textViewDrinkOrder)
        imageViewDrinkOrder = findViewById(R.id.imageViewDrinkOrder)
        val textViewNotes: TextView = findViewById(R.id.textViewNote)

        val notes = intent.getStringExtra(DrinkDetailActivity.NOTES)
        if (!notes.isNullOrEmpty()) {
            textViewNotes.text = notes
        } else {
            textViewNotes.text = getString(R.string.no_notes)
        }

        val orderedDrink = intent.getSerializableExtra(DrinkDetailActivity.ORDERED_DRINK) as? Drink
        if (orderedDrink != null) {
            val orderedDrinkText = getString(R.string.ordered_drink_format, orderedDrink.strDrink)
            textViewOrderedDrink.text = orderedDrinkText
            Glide.with(this).load(orderedDrink.strDrinkThumb).into(imageViewDrinkOrder)
        } else {
            textViewOrderedDrink.text = getString(R.string.no_drink_ordered)
        }
    }

    fun returnToMainScreen(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
