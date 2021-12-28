package com.codingtroops.restaurantsapp

class ToggleRestaurantUseCase {
    private val repository = RestaurantsRepository()
    suspend operator fun invoke( id: Int,
                                 oldValue: Boolean): List<Restaurant> {
        val updatedIsFavorite = oldValue.not()
        return repository.toggleFavoriteRestaurant(id, updatedIsFavorite)
    }
}