package com.codingtroops.restaurantsapp.restaurants.domain

import com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository


class GetSortedRestaurantsUseCase {
    private val repository: IRestaurantsRepository = RestaurantsRepository()
        suspend operator fun invoke(): List<Restaurant> {
            return repository.getRestaurants()
                .sortedByDescending { it.isShutdown }
        }
}