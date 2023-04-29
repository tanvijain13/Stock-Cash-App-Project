package com.example.stockscashapp.api
import retrofit2.http.GET
import retrofit2.Call
import com.example.stockscashapp.data.models.StockData

//defining the endpoint
interface StocksApiService {
    @GET("portfolio.json")
    fun getPortfolio(): Call<StockData>
}