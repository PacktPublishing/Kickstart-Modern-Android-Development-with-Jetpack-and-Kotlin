package com.codingtroops.restaurantsapp.restaurants.data

import com.codingtroops.restaurantsapp.*
import com.codingtroops.restaurantsapp.restaurants.data.local.LocalRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.local.PartialLocalRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.local.RestaurantsDb
import com.codingtroops.restaurantsapp.restaurants.data.remote.RestaurantsApiService
import com.codingtroops.restaurantsapp.restaurants.domain.IRestaurantsRepository
import com.codingtroops.restaurantsapp.restaurants.domain.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException

class RestaurantsRepository: IRestaurantsRepository {
    private var restInterface: RestaurantsApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-db-default-rtdb.firebaseio.com/")
            .build()
            .create(RestaurantsApiService::class.java)
    private var restaurantsDao = RestaurantsDb.getDaoInstance(
        RestaurantsApplication.getAppContext()
    )

    override suspend fun toggleFavoriteRestaurant(
        id: Int,
        value: Boolean
    ) = withContext(Dispatchers.IO) {
        restaurantsDao.update(
            PartialLocalRestaurant(id = id, isFavorite = value)
        )
        restaurantsDao.getAll().map {
            Restaurant(it.id, it.title, it.description, it.isFavorite, it.isShutdown)
        }
    }

    override suspend fun getAllRestaurants(): List<Restaurant> {
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
            return@withContext restaurantsDao.getAll().map {
                Restaurant(it.id, it.title, it.description, it.isFavorite, it.isShutdown)
            }
        }
    }

    private suspend fun refreshCache() {
        val remoteRestaurants = restInterface.getRestaurants()
        val favoriteRestaurants = restaurantsDao.getAllFavorited()
        restaurantsDao.addAll(remoteRestaurants.map {
            LocalRestaurant(it.id, it.title, it.description, false, it.isShutdown)
        })
        restaurantsDao.updateAll(
            favoriteRestaurants.map {
                PartialLocalRestaurant(it.id, true)
            })
    }
}