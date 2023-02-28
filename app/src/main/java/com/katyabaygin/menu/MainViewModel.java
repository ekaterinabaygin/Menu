package com.katyabaygin.menu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Drink>> drinks = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Application application) {
        super(application);
        loadDrinks();

    }

    public LiveData<List<Drink>> getDrinks() {
        return drinks;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loadDrinks() {
        Boolean loading = isLoading.getValue();
        if (loading != null && loading) {
            return;
        }
        Disposable disposable = ApiFactory.apiService.loadDrinks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> isLoading.setValue(true))
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(DrinkResponse -> {
                    List<Drink> loadedDrinks = drinks.getValue();
                    if (loadedDrinks != null) {
                        loadedDrinks.addAll(DrinkResponse.getDrinks());
                        drinks.setValue(loadedDrinks);
                    } else {
                        drinks.setValue(DrinkResponse.getDrinks());

                    }
                }, throwable -> {


                });

        compositeDisposable.add(disposable);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    private LiveData<Integer> orderedDrinksCount;

    public LiveData<Integer> getOrderedDrinksCount() {
        if (orderedDrinksCount == null) {
            orderedDrinksCount = getOrderedDrinksCount();
        }
        return orderedDrinksCount;
    }

}

