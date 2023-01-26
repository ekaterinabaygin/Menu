package com.katyabaygin.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;

public class DrinkDetailActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK = "drinks";

    private ImageView imageViewDrinkDetail;
    private TextView textViewDrinkDetail;
    private EditText textViewNotes;
    private Button button;
    private DrinkDataBase drinkDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        button = findViewById(R.id.button);
        initViews();

        drinkDataBase = Room.databaseBuilder(getApplicationContext(),
                DrinkDataBase.class, "drink_database").build();

        Drink drink = (Drink) getIntent().getSerializableExtra(EXTRA_DRINK);

        if (drink != null) {
            Glide.with(this)
                    .load(drink.getStrDrinkThumb())
                    .into(imageViewDrinkDetail);
            textViewDrinkDetail.setText(drink.getStrDrink());
            textViewNotes.setText(drink.getDrinkNote());
            textViewNotes.setHint("Add your note here)");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = OrderSuccess.newIntent(DrinkDetailActivity.this, button);
                startActivity(intent);
            }

        });
    }

    private void initViews() {
        imageViewDrinkDetail = findViewById(R.id.imageViewDrinkDetail);
        textViewDrinkDetail = findViewById(R.id.textViewDrinkDetail);
        textViewNotes = findViewById(R.id.textViewNotes);
        button = findViewById(R.id.button);
    }

    public static Intent newIntent(Context context, Drink drink) {
        Intent intent = new Intent(context, DrinkDetailActivity.class);
        intent.putExtra(EXTRA_DRINK, drink);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;


    }


}
