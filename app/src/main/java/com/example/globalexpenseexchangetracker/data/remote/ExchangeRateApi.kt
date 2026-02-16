package com.example.globalexpenseexchangetracker.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateApi {

    // @GET defines the endpoint URL.
    // "suspend" means Coroutines will run this safely in the background so the app doesn't freeze.
    @GET("latest")
    suspend fun getLatestRates(
        @Query("base") baseCurrency: String = "USD"
    ): ExchangeRateDto

}