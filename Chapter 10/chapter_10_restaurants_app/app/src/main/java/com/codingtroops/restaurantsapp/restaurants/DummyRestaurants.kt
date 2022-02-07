package com.codingtroops.restaurantsapp.restaurants

import com.codingtroops.restaurantsapp.restaurants.data.remote.RemoteRestaurant
import com.codingtroops.restaurantsapp.restaurants.domain.Restaurant

object DummyContent {
    fun getDomainRestaurants() = arrayListOf(
        Restaurant(0, "title0", "description0", false),
        Restaurant(1, "title1", "description1", false),
        Restaurant(2, "title2", "description2", false),
        Restaurant(3, "title3", "description3", false)
    )
    fun getRemoteRestaurants() = getDomainRestaurants().map {
        RemoteRestaurant(it.id, it.title, it.description)
    }
}
