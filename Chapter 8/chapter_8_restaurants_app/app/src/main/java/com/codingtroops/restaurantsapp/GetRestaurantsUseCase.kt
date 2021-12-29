package com.codingtroops.restaurantsapp

class GetRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getAllRestaurants()
            .sortedBy { it.title }
    }
}

