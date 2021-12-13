package com.codingtroops.restaurantsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


class RestaurantsViewModel() : ViewModel() {

    private val repository = RestaurantsRepository()

    val state = mutableStateOf(RestaurantsScreenState(
        restaurants = listOf(),
        isLoading = true,
        error = null
    ))

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        state.value = state.value.copy(error = exception.message)
    }

    init {
        getRestaurants()
    }

    fun toggleFavorite(itemId: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants =
                repository.toggleFavoriteRestaurant(itemId, oldValue)
            state.value = state.value.copy(restaurants = updatedRestaurants)
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(errorHandler) {
            val restaurants = repository.getAllRestaurants()
            state.value = state.value.copy(
                restaurants = restaurants,
                isLoading = false)
        }
    }

}