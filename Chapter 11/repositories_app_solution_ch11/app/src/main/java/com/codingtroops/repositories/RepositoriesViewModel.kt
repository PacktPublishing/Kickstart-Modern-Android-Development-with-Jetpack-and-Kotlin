package com.codingtroops.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow


class RepositoriesViewModel(
    private val reposPagingSource: RepositoriesPagingSource = RepositoriesPagingSource()
) : ViewModel() {
    val repositories: Flow<PagingData<Repository>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            reposPagingSource
        }).flow.cachedIn(viewModelScope)
}

