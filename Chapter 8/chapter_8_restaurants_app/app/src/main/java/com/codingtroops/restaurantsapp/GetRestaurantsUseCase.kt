package com.codingtroops.restaurantsapp

class GetRestaurantsUseCase {
    private val repository = RestaurantsRepository()
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getAllRestaurants()
            .sortedByDescending { it.isShutdown }
    }
}