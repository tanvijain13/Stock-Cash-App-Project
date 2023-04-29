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
            Log.e("MainViewModel", "getPortfolioDataLog: ${e.message}")
        }
        return null
    }

}
