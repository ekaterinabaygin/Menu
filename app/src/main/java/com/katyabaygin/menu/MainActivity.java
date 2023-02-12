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

        setupViewModel();
        setupRecyclerView();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getDrinks().observe(this, drinks -> {
            if (drinks != null) {
                drinkAdapter.setDrinks(drinks);
                progressBarLoading.setVisibility(View.GONE);
            }
        });

        progressBarLoading.setVisibility(View.VISIBLE);
        viewModel.loadDrinks();
    }

    private void setupRecyclerView() {
        recyclerViewDrinks = findViewById(R.id.recyclerViewDrinks);
        drinkAdapter = new DrinkAdapter();
        recyclerViewDrinks.setAdapter(drinkAdapter);
        recyclerViewDrinks.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel.getDrinks().observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(List<Drink> drinks) {
                drinkAdapter.setDrinks(drinks);
                progressBarLoading.setVisibility(View.GONE);
            }
        });
    }}




