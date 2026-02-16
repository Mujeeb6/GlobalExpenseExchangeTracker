package com.example.globalexpenseexchangetracker.domain.model

data class ExchangeRate(
    val baseCurrency: String,
    val rates: Map<String, Double>
)