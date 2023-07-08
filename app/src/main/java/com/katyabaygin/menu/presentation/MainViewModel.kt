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
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _drinks = MutableLiveData<List<Drink>>()
    val drinks: LiveData<List<Drink>>
        get() = _drinks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadDrinks() {
        val loading = _isLoading.value
        if (loading != null && loading) {
            return
        }

        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    ApiFactory.apiService.loadDrinks()
                }
                val loadedDrinks = _drinks.value?.toMutableList()
                if (loadedDrinks != null) {
                    loadedDrinks.addAll(response.toMutableList())
                    _drinks.postValue(loadedDrinks)
                } else {
                    _drinks.postValue(response.toMutableList())
                }
            } catch (e: Exception) {
                // Handle the exception
            } finally {
                _isLoading.value = false
            }
        }
    }
}
