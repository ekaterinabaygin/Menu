package com.katyabaygin.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MyOrdersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DrinkAdapter drinkAdapter;
    public static final String EXTRA_DRINK = "drinks";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        recyclerView = findViewById(R.id.recyclerViewMyOrders);
        drinkAdapter = new DrinkAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(drinkAdapter);

    }





    int drink = (Integer)getIntent().getExtras().get(EXTRA_DRINK);


}
