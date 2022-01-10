package com.codingtroops.restaurantsapp.restaurants.domain

import com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository


class GetSortedRestaurantsUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants()
            .sortedBy { it.title }
    }
}