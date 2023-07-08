package com.katyabaygin.menu.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.katyabaygin.menu.R
import com.katyabaygin.menu.domain.Drink

class DrinkAdapter : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    private var drinks = ArrayList<Drink>()
    private var onDrinkClickListener: OnDrinkClickListener? = null
    private var onReachEndListener: OnReachEndListener? = null

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener?) {
        this.onReachEndListener = onReachEndListener
    }

    fun setOnDrinkClickListener(onDrinkClickListener: OnDrinkClickListener?) {
        this.onDrinkClickListener = onDrinkClickListener
    }

    fun setDrinks(drinks: ArrayList<Drink>) {
        this.drinks = drinks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.drink_item,
            parent,
            false
        )
        return DrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink = drinks[position]
        Glide.with(holder.itemView)
            .load(drink.strDrinkThumb)
            .into(holder.imageViewDrink)
        holder.textViewDrinkName.text = drink.strDrink

        if (position == drinks.size - 1 && onReachEndListener != null) {
            onReachEndListener!!.onReachEnd()
        }

        holder.itemView.setOnClickListener {
            onDrinkClickListener?.onDrinkClick(drink)
        }
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }

    interface OnDrinkClickListener {
        fun onDrinkClick(drink: Drink)
    }

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewDrink: ImageView = itemView.findViewById(R.id.imageViewDrink)
        val textViewDrinkName: TextView = itemView.findViewById(R.id.textViewDrinkName)
    }
}
