package com.codingtroops.restaurantsapp.restaurants.domain

import com.codingtroops.restaurantsapp.Restaurant
import com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository

class GetRestaurantsUseCase {
    private val repository: IRestaurantsRepository = RestaurantsRepository()
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getAllRestaurants()
            .sortedByDescending { it.isShutdown }
    }
}