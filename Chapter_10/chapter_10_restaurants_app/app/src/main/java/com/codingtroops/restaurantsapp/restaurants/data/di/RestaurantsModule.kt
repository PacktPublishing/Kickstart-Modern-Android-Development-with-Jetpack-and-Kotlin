package com.codingtroops.restaurantsapp.restaurants.data.di

import android.content.Context
import androidx.room.Room
import com.codingtroops.restaurantsapp.restaurants.data.local.RestaurantsDao
import com.codingtroops.restaurantsapp.restaurants.data.local.RestaurantsDb
import com.codingtroops.restaurantsapp.restaurants.data.remote.RestaurantsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RestaurantsModule {
    @Provides
    fun provideRoomDao(database: RestaurantsDb): RestaurantsDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): RestaurantsDb {
        return Room.databaseBuilder(
            appContext,
            RestaurantsDb::class.java,
            "restaurants_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://restaurants-db-default-rtdb.firebaseio.com/")
            .build()
    }

    @Provides
    fun provideRetrofitApi(retrofit: Retrofit): RestaurantsApiService {
        return retrofit.create(RestaurantsApiService::class.java)
    }
}
