package com.example.globalexpenseexchangetracker.data.repository

import com.example.globalexpenseexchangetracker.data.local.ExpenseDao
import com.example.globalexpenseexchangetracker.data.local.ExpenseEntity
import com.example.globalexpenseexchangetracker.data.remote.ExchangeRateApi
import com.example.globalexpenseexchangetracker.domain.model.ExchangeRate
import com.example.globalexpenseexchangetracker.domain.model.Expense
import com.example.globalexpenseexchangetracker.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// This class implements the interface from the Domain layer
class CurrencyRepositoryImpl(
    private val api: ExchangeRateApi,
    private val dao: ExpenseDao
) : CurrencyRepository {

    // 1. Fetch from the Internet (Retrofit)
    override suspend fun getLatestExchangeRates(baseCurrency: String): ExchangeRate {
        return api.getLatestRates(baseCurrency).toExchangeRate()
    }

    // 2. Read from Local Database (Room)
    override fun getSavedExpenses(): Flow<List<Expense>> {
        // Map the database entities back to clean Domain models
        return dao.getAllExpenses().map { entities ->
            entities.map { it.toExpense() }
        }
    }

    // 3. Save to Local Database (Room)
    override suspend fun saveExpense(expense: Expense) {
        val entity = ExpenseEntity(
            title = expense.title,
            amount = expense.amount,
            currency = expense.currency,
            date = expense.date
        )
        dao.insertExpense(entity)
    }
    override suspend fun deleteExpense(expense: Expense) {
        // Convert the clean Domain model back into a Database Entity
        val entity = ExpenseEntity(
            id = expense.id, // The ID is crucial so Room knows exactly which one to delete!
            title = expense.title,
            amount = expense.amount,
            currency = expense.currency,
            date = expense.date
        )
        dao.deleteExpense(entity)
    }
}