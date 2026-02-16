package com.example.globalexpenseexchangetracker.data.remote

import com.example.globalexpenseexchangetracker.domain.model.ExchangeRate

// This exactly matches the JSON format from the internet API
data class ExchangeRateDto(
    val base: String,
    val rates: Map<String, Double>
) {
    // This "mapper" converts the internet data into our clean Domain model
    fun toExchangeRate(): ExchangeRate {
        return ExchangeRate(
            baseCurrency = base,
            rates = rates
        )
    }
}