package com.codingtroops.restaurantsapp

import com.codingtroops.restaurantsapp.restaurants.data.local.LocalRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.local.PartialLocalRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.local.RestaurantsDao
import kotlinx.coroutines.delay

class FakeRoomDao : RestaurantsDao {
    private var restaurants = HashMap<Int, LocalRestaurant>()
    override suspend fun getAll()
            : List<LocalRestaurant> {
        delay(1000)
        return restaurants.values.toList()
    }

    override suspend fun addAll(
        restaurants: List<LocalRestaurant>
    ) {
        restaurants.forEach { this.restaurants[it.id] = it }
    }

    override suspend fun update(
        partialRestaurant: PartialLocalRestaurant
    ) {
        delay(1000)
        updateRestaurant(partialRestaurant)
    }

    override suspend fun updateAll(
        partialRestaurants: List<PartialLocalRestaurant>
    ) {
        delay(1000)
        partialRestaurants.forEach { updateRestaurant(it) }
    }

    override suspend fun getAllFavorited()
            : List<LocalRestaurant> {
        return restaurants.values.toList()
            .filter { it.isFavorite }
    }

    private fun updateRestaurant(
        partialRestaurant: PartialLocalRestaurant
    ) {
        val restaurant = this.restaurants[partialRestaurant.id]
        if (restaurant != null)
            this.restaurants[partialRestaurant.id] =
                restaurant.copy(isFavorite = partialRestaurant.isFavorite)
    }
}
