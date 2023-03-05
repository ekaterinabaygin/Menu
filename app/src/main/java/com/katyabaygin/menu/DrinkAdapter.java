package com.katyabaygin.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private List<Drink> drinks = new ArrayList<>();
    private OnDrinkClickListener onDrinkClickListener;
    private OnReachEndListener onReachEndListener;

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setOnDrinkClickListener(OnDrinkClickListener onDrinkClickListener) {
        this.onDrinkClickListener = onDrinkClickListener;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.drink_item,
                parent,
                false
        );
        return new DrinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drink drink = drinks.get(position);
        Glide.with(holder.itemView)
                .load(drink.getStrDrinkThumb())
                .into(holder.imageViewDrink);
        holder.textViewDrinkName.setText(drink.getStrDrink());



        if (position == drinks.size() - 1 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDrinkClickListener != null) {
                    onDrinkClickListener.onDrinkClick(drink);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }

    interface OnReachEndListener {
        void onReachEnd();
    }

    interface OnDrinkClickListener {
        void onDrinkClick(Drink drink);
    }

    static class DrinkViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewDrink;
        private final TextView textViewDrinkName;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewDrink = itemView.findViewById(R.id.imageViewDrink);
            textViewDrinkName = itemView.findViewById(R.id.textViewDrinkName);
        }
    }
}
