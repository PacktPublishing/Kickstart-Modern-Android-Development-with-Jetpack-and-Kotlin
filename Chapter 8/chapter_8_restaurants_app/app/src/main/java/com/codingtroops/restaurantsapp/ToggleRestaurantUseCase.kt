package com.codingtroops.restaurantsapp

class ToggleRestaurantUseCase {
    private val repository: RestaurantsRepository = RestaurantsRepository()
    suspend operator fun invoke(id: Int,
                                oldValue: Boolean): List<Restaurant> {
        val newFav = oldValue.not()
        return repository.toggleFavoriteRestaurant(id, newFav)
    }
}