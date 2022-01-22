package com.codingtroops.restaurantsapp.restaurants.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class LocalRestaurant(
    @PrimaryKey()
    @ColumnInfo(name = "r_id")
    val id: Int,
    @ColumnInfo(name = "r_title")
    val title: String,
    @ColumnInfo(name = "r_description")
    val description: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)