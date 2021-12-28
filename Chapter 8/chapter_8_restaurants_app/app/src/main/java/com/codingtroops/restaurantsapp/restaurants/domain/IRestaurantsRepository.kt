package com.codingtroops.restaurantsapp.restaurants.domain

import com.codingtroops.restaurantsapp.Restaurant

interface IRestaurantsRepository {
    suspend fun getAllRestaurants(): List<Restaurant>
    suspend fun toggleFavoriteRestaurant(id: Int, value: Boolean): List<Restaurant>
}