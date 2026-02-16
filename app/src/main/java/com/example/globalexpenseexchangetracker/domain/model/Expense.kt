package com.example.globalexpenseexchangetracker.domain.model

data class Expense(
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val currency: String,
    val date: Long
)