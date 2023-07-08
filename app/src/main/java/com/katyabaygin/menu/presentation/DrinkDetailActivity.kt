package com.katyabaygin.menu.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.katyabaygin.menu.R
import com.katyabaygin.menu.domain.Drink

@Suppress("DEPRECATION")
class DrinkDetailActivity : AppCompatActivity() {

    private lateinit var imageViewDrinkDetail: ImageView
    private lateinit var textViewDrinkDetail: TextView
    private lateinit var textViewNotes: EditText
    private lateinit var button: Button

    private lateinit var drink: Drink

    companion object {
        const val EXTRA_DRINK = "drinks"
        const val ORDERED_DRINK = "ordered"
        const val NOTES = "notes"

        fun newIntent(context: Context, drink: Drink): Intent {
            val intent = Intent(context, DrinkDetailActivity::class.java)
            intent.putExtra(EXTRA_DRINK, drink)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_detail)
        initViews()

        drink = (intent.getSerializableExtra(EXTRA_DRINK) as? Drink)!!

        drink.let {
            Glide.with(this)
                .load(it.strDrinkThumb)
                .into(imageViewDrinkDetail)
            textViewDrinkDetail.text = it.strDrink
            textViewNotes.setText(it.drinkNote)
            textViewNotes.hint = "Add your note here"
        }

        button.setOnClickListener {
            val intent = Intent(this, MyOrdersActivity::class.java)
            drink.let {
                it.drinkNote = textViewNotes.text.toString()
                intent.putExtra(ORDERED_DRINK, it)
                intent.putExtra(NOTES, textViewNotes.text.toString())
                startActivity(intent)
                showToast(it)
            }
        }
    }

    private fun showToast(drink: Drink) {
        val toast = Toast.makeText(
            applicationContext,
            "Your order for ${drink.strDrink} is successful!",
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun initViews() {
        imageViewDrinkDetail = findViewById(R.id.imageViewDrinkDetail)
        textViewDrinkDetail = findViewById(R.id.textViewDrinkDetail)
        textViewNotes = findViewById(R.id.textViewNotes)
        button = findViewById(R.id.button)
    }
}
