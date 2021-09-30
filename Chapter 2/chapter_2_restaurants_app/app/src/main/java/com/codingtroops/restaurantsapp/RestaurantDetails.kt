package com.codingtroops.restaurantsapp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RestaurantsDetailsScreen(restaurantId: Int) {
    val viewModelFactory = RestaurantDetailsVMFactory(restaurantId)
    val viewModel: RestaurantDetailsVM = viewModel(factory = viewModelFactory)
    Text(viewModel.restaurantState.value?.title ?: "")
}

class RestaurantDetailsVM(restaurantId: Int) : ViewModel() {
    val restaurantState: MutableState<Restaurant?> = mutableStateOf(null)

    init {
        restaurantState.value = dummyRestaurants.firstOrNull { it.id == restaurantId }
    }
}

class RestaurantDetailsVMFactory(private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantDetailsVM::class.java)) {
            return RestaurantDetailsVM(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
