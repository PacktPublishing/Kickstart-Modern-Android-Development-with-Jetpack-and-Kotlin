package com.codingtroops.restaurantsapp

import retrofit2.http.GET


interface RestaurantsApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>
}