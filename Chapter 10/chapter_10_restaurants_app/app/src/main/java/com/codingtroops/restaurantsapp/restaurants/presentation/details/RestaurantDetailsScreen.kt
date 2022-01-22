package com.codingtroops.restaurantsapp.restaurants.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingtroops.restaurantsapp.restaurants.presentation.list.RestaurantDetails
import com.codingtroops.restaurantsapp.restaurants.presentation.list.RestaurantIcon

@Composable
fun RestaurantDetailsScreen() {
    val viewModel: RestaurantDetailsViewModel = viewModel()
    val item = viewModel.state.value
    if (item != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.padding(top = 32.dp, bottom = 32.dp)
            )
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.padding(bottom = 32.dp),
                Alignment.CenterHorizontally
            )
            Text("More info coming soon!")
        }
    }
}