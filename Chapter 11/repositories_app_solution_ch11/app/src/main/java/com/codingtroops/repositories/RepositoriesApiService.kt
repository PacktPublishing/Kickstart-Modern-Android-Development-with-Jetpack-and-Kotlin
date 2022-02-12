package com.codingtroops.repositories

import retrofit2.http.GET


interface RepositoriesApiService {
    @GET("repositories?q=mobile&sort=stars&page=1&per_page=20")
    suspend fun getRepositories(): RepositoriesResponse
}