package com.codingtroops.restaurantsapp


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Restaurant::class], version = 2, exportSchema = false)
abstract class RestaurantsDatabase : RoomDatabase() {

    abstract val dao: RestaurantsDao

    companion object {
        @Volatile
        private var INSTANCE: RestaurantsDatabase? = null

        fun getInstance(context: Context): RestaurantsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantsDatabase::class.java,
                        "restaurants_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}