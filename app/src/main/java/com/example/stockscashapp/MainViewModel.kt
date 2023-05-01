package com.example.stockscashapp

import androidx.lifecycle.ViewModel
import com.example.stockscashapp.api.StocksApiService
import com.example.stockscashapp.data.models.StockData
import retrofit2.Response
import retrofit2.awaitResponse
import android.util.Log
import com.google.gson.GsonBuilder
import java.lang.Exception

class MainViewModel(private val apiService: StocksApiService): ViewModel() {

    // Suspend function to get portfolio data
    suspend fun getPortfolioData(): StockData? {
        try {
            val gson = GsonBuilder().setLenient().create() // Create a Gson instance with lenient parsing
            val response: Response<StockData> = apiService.getPortfolio().awaitResponse()
            return response.body()
        } catch (e: Exception) {
            MyLogger().log("MainViewModel", "getPortfolioDataLog: ${e.message}")
        }
        return null
    }
    /* test case scenario
    suspend fun getPortfolioData(): StockData? {
        try {
            val stockList = StockData(
                listOf(
                    Stock("AAPL", "Apple Inc.", "USD", 135, 10, 1619805891),
                    Stock("GOOGL", "Alphabet Inc.", "USD", 2300, 5, 1619805891),
                    Stock("MSFT", "Microsoft Corporation", "USD", 255, 20, 1619805891)
                )
            )
            return stockList
        } catch (e: Exception) {
            MyLogger().log("MainViewModel", "getPortfolioDataLog: ${e.message}")
        }
        return null
    }*/
    class MyLogger {
        fun log(tag: String, message: String) {
            if (BuildConfig.DEBUG) {
                // Use println or another logging library
                println("$tag: $message")
            } else {
                // Use android.util.Log
                Log.d(tag, message)
            }
        }
    }

}
