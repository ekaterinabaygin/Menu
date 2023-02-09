package com.katyabaygin.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarLoading = findViewById(R.id.progressBarLoading);

        recyclerViewDrinks = findViewById(R.id.recyclerViewDrinks);
        drinkAdapter = new DrinkAdapter();
        recyclerViewDrinks.setAdapter(drinkAdapter);
        recyclerViewDrinks.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getDrinks().observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                drinkAdapter.setDrinks(drinks);
            }
        });
        viewModel.loadDrinks();
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {

                    progressBarLoading.setVisibility(View.VISIBLE);
                } else {
                    progressBarLoading.setVisibility(View.GONE);

                }
            }
        });
        drinkAdapter.setOnReachEndListener(new DrinkAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadDrinks();
            }
        });

        drinkAdapter.setOnDrinkClickListener(new DrinkAdapter.OnDrinkClickListener() {
            @Override
            public void onDrinkClick(Drink drink) {
                Intent intent = DrinkDetailActivity.newIntent(MainActivity.this, drink);
                startActivity(intent);
            }
        });

    }
}



