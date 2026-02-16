package com.example.globalexpenseexchangetracker.domain.repository

import com.example.globalexpenseexchangetracker.domain.model.ExchangeRate
import com.example.globalexpenseexchangetracker.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getLatestExchangeRates(baseCurrency: String): ExchangeRate
    fun getSavedExpenses(): Flow<List<Expense>>
    suspend fun saveExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
}