package com.codingtroops.restaurantsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.codingtroops.restaurantsapp.ui.theme.RestaurantsAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val destinationState: MutableState<Destination> =
                remember { mutableStateOf(Destination.RestaurantList) }
            RestaurantsAppTheme {
                val destination = destinationState.value
                if (destination is Destination.RestaurantList)
                    RestaurantsScreen { id ->
                        destinationState.value = Destination.RestaurantDetails(id)
                    }
                else if (destination is Destination.RestaurantDetails)
                    RestaurantsDetailsScreen(destination.id)
            }
        }
    }
}

sealed class Destination {
    object RestaurantList : Destination()
    data class RestaurantDetails(val id: Int) : Destination()
}