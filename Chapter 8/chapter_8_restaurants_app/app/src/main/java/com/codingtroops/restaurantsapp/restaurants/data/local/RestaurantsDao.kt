package com.codingtroops.restaurantsapp.restaurants.data.local


import androidx.room.*
import com.codingtroops.restaurantsapp.restaurants.data.PartialRestaurant
import com.codingtroops.restaurantsapp.restaurants.data.Restaurant


@Dao
interface RestaurantsDao {
    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<Restaurant>)

    @Update(entity = Restaurant::class)
    suspend fun update(partialRestaurant: PartialRestaurant)

    @Update(entity = Restaurant::class)
    suspend fun updateAll(partialRestaurants: List<PartialRestaurant>)

    @Query("SELECT * FROM restaurants WHERE is_favorite = 1")
    suspend fun getAllFavorited(): List<Restaurant>

}