package com.codingtroops.restaurantsapp.restaurants.data

import com.codingtroops.restaurantsapp.*
import com.codingtroops.restaurantsapp.restaurants.data.local.LocalRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.local.PartialLocalRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.local.RestaurantsDao
import com.codingtroops.restaurantsapp.restaurants.data.remote.RestaurantsApiService
import com.codingtroops.restaurantsapp.restaurants.domain.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantsRepository @Inject constructor(
    private val restInterface: RestaurantsApiService,
    private val restaurantsDao: RestaurantsDao
) {
    suspend fun toggleFavoriteRestaurant(
        id: Int,
        value: Boolean
    ) = withContext(Dispatchers.IO) {
        restaurantsDao.update(
            PartialLocalRestaurant(id = id, isFavorite = value)
        )
    }

    suspend fun getRestaurants() : List<Restaurant> {
        return withContext(Dispatchers.IO) {
            return@withContext restaurantsDao.getAll().map {
                Restaurant(it.id, it.title, it.description, it.isFavorite)
            }
        }
    }

    suspend fun loadRestaurants() {
        return withContext(Dispatchers.IO) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (restaurantsDao.getAll().isEmpty())
                            throw Exception(
                                "Something went wrong. " +
                                        "We have no data.")
                    }
                    else -> throw e
                }
            }
        }
    }

    private suspend fun refreshCache() {
        val remoteRestaurants = restInterface.getRestaurants()
        val favoriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(remoteRestaurants.map {
            LocalRestaurant(it.id, it.title, it.description, false)
        })
        restaurantsDao.updateAll(
            favoriteRestaurants.map {
                PartialLocalRestaurant(id = it.id, isFavorite = true)
            })
    }
}