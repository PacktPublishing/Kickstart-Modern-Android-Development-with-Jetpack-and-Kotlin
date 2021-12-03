package com.codingtroops.restaurantsapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.UnknownHostException


class RestaurantsViewModel() : ViewModel() {
    private var restInterface: RestaurantsApiService
    private var localDatabase = RestaurantsDatabase.getInstance(
        RestaurantsApplication.getAppContext())

    val state = mutableStateOf(emptyList<Restaurant>())

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-db-default-rtdb.firebaseio.com/")
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)
        getRestaurants()
    }

    fun toggleFavorite(itemId: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants = updateRestaurant(itemId, oldValue)
            state.value = updatedRestaurants
        }
    }

    private suspend fun updateRestaurant(itemId: Int, oldValue: Boolean): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            localDatabase.dao.update(PartialRestaurant(itemId, !oldValue))
            return@withContext localDatabase.dao.getAll()
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(errorHandler) {
            state.value = getAllRestaurants()
        }
    }

    private suspend fun getAllRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
                val favoriteRestaurants = localDatabase.dao.getAllFavorited()
                val remoteRestaurants = restInterface.getRestaurants()
                localDatabase.dao.addAll(remoteRestaurants)
                localDatabase.dao.updateAll(
                    favoriteRestaurants.map {
                        PartialRestaurant(it.id, true)
                    })
            } catch (e: IOException) {
                if (localDatabase.dao.getAll().isEmpty())
                    throw Exception("No data to show")
            }
            return@withContext localDatabase.dao.getAll()
        }
    }

}