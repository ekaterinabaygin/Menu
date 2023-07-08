package com.katyabaygin.menu.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.katyabaygin.menu.data.ApiFactory
import com.katyabaygin.menu.domain.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val drinkRepository = ApiFactory.getDrinkRepository(application)

    private val _drinks = MutableLiveData<List<Drink>>()
    val drinks: LiveData<List<Drink>> get() = _drinks

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun loadDrinks() {
        val loading = _isLoading.value
        if (loading != null && loading) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)

            try {
                val loadedDrinks = drinkRepository.getDrinks()
                _drinks.postValue(loadedDrinks.value)
            } catch (_: Exception) {
            }

            _isLoading.postValue(false)
        }
    }
}
