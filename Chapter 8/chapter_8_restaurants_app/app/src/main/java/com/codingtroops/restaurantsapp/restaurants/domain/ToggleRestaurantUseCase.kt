package com.codingtroops.restaurantsapp.restaurants.domain

import com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository

class ToggleRestaurantUseCase {
    private val repository: IRestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()
    suspend operator fun invoke( id: Int,
                                 oldValue: Boolean): List<Restaurant> {
        val updatedIsFavorite = oldValue.not()
        repository.toggleFavoriteRestaurant(id, updatedIsFavorite)
        return getSortedRestaurantsUseCase()
    }
}