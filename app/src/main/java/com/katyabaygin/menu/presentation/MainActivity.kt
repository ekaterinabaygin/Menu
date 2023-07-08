package com.katyabaygin.menu.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.katyabaygin.menu.R
import com.katyabaygin.menu.domain.Drink


class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DRINK = "drinks"
    }


    lateinit var viewModel: MainViewModel
    private lateinit var recyclerViewDrinks: RecyclerView
    private lateinit var drinkAdapter: DrinkAdapter
    private lateinit var progressBarLoading: ProgressBar

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBarLoading = findViewById(R.id.progressBarLoading)
        recyclerViewDrinks = findViewById(R.id.recyclerViewDrinks)
        drinkAdapter = DrinkAdapter()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        recyclerViewDrinks.adapter = drinkAdapter
        recyclerViewDrinks.layoutManager = GridLayoutManager(this, 2)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        auth = FirebaseAuth.getInstance()

        viewModel.drinks.observe(this) { drinks ->
            drinkAdapter.setDrinks(drinks as ArrayList<Drink>)
        }
        viewModel.loadDrinks()
        viewModel.isLoading.observe(this) { isLoading ->
            progressBarLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        drinkAdapter.setOnReachEndListener(object : DrinkAdapter.OnReachEndListener {
            override fun onReachEnd() {
                viewModel.loadDrinks()
            }
        })
        drinkAdapter.setOnDrinkClickListener(object : DrinkAdapter.OnDrinkClickListener {
            override fun onDrinkClick(drink: Drink) {
                // Handle drink click
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_my_orders) {
            val intent = Intent(this, MyOrdersActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
