package com.codingtroops.restaurantsapp

import com.codingtroops.restaurantsapp.restaurants.DummyContent
import com.codingtroops.restaurantsapp.restaurants.data.RestaurantsRepository
import com.codingtroops.restaurantsapp.restaurants.domain.GetSortedRestaurantsUseCase
import com.codingtroops.restaurantsapp.restaurants.domain.ToggleRestaurantUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ToggleRestaurantUseCaseTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun toggleRestaurant_IsUpdatingFavoriteField() = scope.runTest {
        // Setup useCase
        val restaurantsRepository = RestaurantsRepository(
            FakeApiService(),
            FakeRoomDao(),
            dispatcher
        )
        val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(restaurantsRepository)
        val useCase = ToggleRestaurantUseCase(
            restaurantsRepository,
            getSortedRestaurantsUseCase
        )

        // Preload data
        restaurantsRepository.loadRestaurants()
        advanceUntilIdle()

        // Execute useCase
        val restaurants = DummyContent.getDomainRestaurants()
        val targetItem = restaurants[0]
        val isFavorite = targetItem.isFavorite
        val updatedRestaurants = useCase(targetItem.id, isFavorite)
        advanceUntilIdle()

        // Assertion
        restaurants[0] = targetItem.copy(isFavorite = !isFavorite)
        assert(updatedRestaurants == restaurants)
    }
}
