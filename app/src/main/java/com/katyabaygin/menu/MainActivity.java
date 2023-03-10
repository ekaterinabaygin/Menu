package com.katyabaygin.menu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView recyclerViewDrinks;
    private DrinkAdapter drinkAdapter;
    private ProgressBar progressBarLoading;
    public static final String EXTRA_DRINK = "drinks";
    private TextView textViewDrinkDetail;




    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarLoading = findViewById(R.id.progressBarLoading);
        recyclerViewDrinks = findViewById(R.id.recyclerViewDrinks);
        drinkAdapter = new DrinkAdapter();
        initViews();




        recyclerViewDrinks.setAdapter(drinkAdapter);
        recyclerViewDrinks.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        auth = FirebaseAuth.getInstance();

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

    private void initViews() {
        textViewDrinkDetail = findViewById(R.id.textViewDrinkDetail);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem myOrdersMenuItem = menu.findItem(R.id.menu_my_orders);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_my_orders) {
            Intent intent = new Intent(this, MyOrdersActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
