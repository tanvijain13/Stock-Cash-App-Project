package com.example.stockscashapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.stockscashapp.api.StocksApiService
import com.example.stockscashapp.data.models.Stock
import com.example.stockscashapp.data.models.StockData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class MainViewModelTest {

    private val apiService: StocksApiService = mockk()

    private val viewModel = MainViewModel(apiService)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `getPortfolioData returns correct data`() = runBlocking {
        val stockList = StockData(listOf(
            Stock("AAPL", "Apple Inc.", "USD", 135, 10, 1619805891),
            Stock("GOOGL", "Alphabet Inc.", "USD", 2300, 5, 1619805891),
            Stock("MSFT", "Microsoft Corporation", "USD", 255, 20, 1619805891),
        ))

        val expectedData = stockList

        val call: Call<StockData> = mockk()
        coEvery { apiService.getPortfolio() } returns call
        coEvery { call.execute() } returns Response.success(expectedData)

        val actualData = viewModel.getPortfolioData()

        assertEquals(expectedData, actualData)
    }


}
