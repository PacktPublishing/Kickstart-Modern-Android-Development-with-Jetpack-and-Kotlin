package com.codingtroops.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyContainer {
    val repositoriesRetrofitClient: RepositoriesApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.github.com/search/")
        .build().create(RepositoriesApiService::class.java)
}