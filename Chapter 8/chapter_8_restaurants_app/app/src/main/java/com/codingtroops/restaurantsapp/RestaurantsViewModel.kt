package com.codingtroops.restaurantsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


class RestaurantsViewModel() : ViewModel() {
    private val getRestaurantsUseCase =
        GetRestaurantsUseCase()
    private val toggleRestaurantsUseCase =
        ToggleRestaurantUseCase()

    private val _state = mutableStateOf(RestaurantsScreenState(
        restaurants = listOf(),
        isLoading = true))
    val state: State<RestaurantsScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(error = exception.message, isLoading = false)
    }

    init {
        getRestaurants()
    }

    fun toggleFavorite(itemId: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants =
                toggleRestaurantsUseCase(itemId, oldValue)
            _state.value = _state.value.copy(restaurants = updatedRestaurants)
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(errorHandler) {
            val restaurants = getRestaurantsUseCase()
            _state.value = _state.value.copy(
                restaurants = restaurants,
                isLoading = false)
        }
    }

}