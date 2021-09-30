package com.codingtroops.restaurantsapp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RestaurantsDetailsScreen(restaurantId: Int) {
    val viewModelFactory = RestaurantDetailsVMFactory(restaurantId)
    val viewModel: RestaurantDetailsVM = viewModel(factory = viewModelFactory)
    Text(viewModel.restaurantState.value?.title ?: "")
}


