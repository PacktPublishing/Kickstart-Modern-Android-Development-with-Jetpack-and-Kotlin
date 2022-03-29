package com.codingtroops.repositories

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class RepositoriesViewModel(
    private val restInterface: RepositoriesApiService
    = DependencyContainer.repositoriesRetrofitClient
) : ViewModel() {
    val repositories = mutableStateOf(emptyList<Repository>())
    init {
        viewModelScope.launch {
            repositories.value = restInterface.getRepositories().repos
        }
    }
}