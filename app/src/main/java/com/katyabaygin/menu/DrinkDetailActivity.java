package com.katyabaygin.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
public class DrinkDetailActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK = "drinks";
    public static final String ORDERED_DRINK = "ordered";
    public static final String NOTES = "notes";


    private ImageView imageViewDrinkDetail;
    private TextView textViewDrinkDetail;
    private EditText textViewNotes;
    private Button button;
    private DrinkDataBase drinkDataBase;
    private DrinkDao drinkDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);
        initViews();
        Drink drink = (Drink) getIntent().getSerializableExtra(EXTRA_DRINK);


        drinkDataBase = DrinkDataBase.getInstance(getApplication());
        drinkDao = drinkDataBase.drinkDao();

        if (drink != null) {
            Glide.with(this)
                    .load(drink.getStrDrinkThumb())
                    .into(imageViewDrinkDetail);
            textViewDrinkDetail.setText(drink.getStrDrink());
            textViewNotes.setText(drink.getDrinkNote());
            textViewNotes.setHint("Add your note here");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrinkDetailActivity.this, MyOrdersActivity.class);
                drink.setDrinkNote(textViewNotes.getText().toString());
                intent.putExtra(ORDERED_DRINK, drink);
                intent.putExtra(NOTES, textViewNotes.getText().toString());
                startActivity(intent);
                showToast(view);
            }
        });
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Your order is successful!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    private void initViews() {
        imageViewDrinkDetail = findViewById(R.id.imageViewDrinkDetail);
        textViewDrinkDetail = findViewById(R.id.textViewDrinkDetail);
        textViewNotes = findViewById(R.id.textViewNotes);
        button = findViewById(R.id.button);
    }

    public static Intent newIntent(Context context, Drink drink){
        Intent intent = new Intent(context, DrinkDetailActivity.class);
        intent.putExtra(EXTRA_DRINK, drink);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (drinkDataBase != null) {
            drinkDataBase.close();
        }
    }}
