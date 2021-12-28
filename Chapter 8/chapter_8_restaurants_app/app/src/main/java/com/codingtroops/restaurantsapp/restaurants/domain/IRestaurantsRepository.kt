package com.codingtroops.restaurantsapp.restaurants.domain

interface IRestaurantsRepository {
    suspend fun loadRestaurants()
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun toggleFavoriteRestaurant(id: Int, value: Boolean)
}