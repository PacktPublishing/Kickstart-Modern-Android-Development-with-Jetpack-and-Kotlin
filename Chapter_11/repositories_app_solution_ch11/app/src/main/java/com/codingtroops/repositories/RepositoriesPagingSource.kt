package com.codingtroops.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState

class RepositoriesPagingSource(
    private val restInterface: RepositoriesApiService
    = DependencyContainer.repositoriesRetrofitClient
) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        try {
            val nextPage = params.key ?: 1
            val repos = restInterface.getRepositories(nextPage).repos
            return LoadResult.Page(
                data = repos,
                prevKey = if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }
}
