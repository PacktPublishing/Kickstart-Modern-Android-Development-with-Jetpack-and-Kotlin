package com.codingtroops.restaurantsapp


class GetSortedRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}