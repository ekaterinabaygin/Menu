package com.katyabaygin.menu;

import static com.katyabaygin.menu.DrinkDetailActivity.NOTES;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

public class MyOrdersActivity extends AppCompatActivity {

    private TextView textViewOrderedDrink;
    private ImageView imageViewDrinkOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        textViewOrderedDrink = findViewById(R.id.textViewDrinkOrder);
        imageViewDrinkOrder = findViewById(R.id.imageViewDrinkOrder);
        TextView textViewNotes = findViewById(R.id.textViewNote);

        String notes = getIntent().getStringExtra(NOTES); // Retrieve the note from the intent extras
        if (notes != null && !notes.isEmpty()) {
            textViewNotes.setText(notes);
        } else {
            textViewNotes.setText("No notes.");
        }


        Drink orderedDrink = (Drink) getIntent().getSerializableExtra(DrinkDetailActivity.ORDERED_DRINK);

        if (orderedDrink != null) {
            textViewOrderedDrink.setText("Ordered drink: " + orderedDrink.getStrDrink());
            Glide.with(this).load(orderedDrink.getStrDrinkThumb()).into(imageViewDrinkOrder);
        } else {
            textViewOrderedDrink.setText("No drink ordered.");
        }


    }

    public void returnToMainScreen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}

