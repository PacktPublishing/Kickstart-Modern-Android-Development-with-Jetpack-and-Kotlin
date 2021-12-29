package com.codingtroops.restaurantsapp

class GetRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()
    suspend operator fun invoke(): List<Restaurant> {
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }
}

