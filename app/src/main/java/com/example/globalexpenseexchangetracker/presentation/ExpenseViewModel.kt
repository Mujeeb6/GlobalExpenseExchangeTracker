package com.example.globalexpenseexchangetracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalexpenseexchangetracker.domain.model.Expense
import com.example.globalexpenseexchangetracker.domain.repository.CurrencyRepository
import com.example.globalexpenseexchangetracker.domain.usecase.ConvertCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()

    private val _rates = MutableStateFlow<Map<String, Double>>(emptyMap())
    val rates: StateFlow<Map<String, Double>> = _rates.asStateFlow()

    init {
        loadExpenses()
        fetchRates()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            repository.getSavedExpenses().collect { listOfExpenses ->
                _expenses.value = listOfExpenses
            }
        }
    }

    private fun fetchRates() {
        viewModelScope.launch {
            try {
                // CHANGED: Fetch live rates based on the British Pound (GBP)
                val rateData = repository.getLatestExchangeRates("GBP")
                _rates.value = rateData.rates
            } catch (e: Exception) {
                // If offline, the map stays empty
            }
        }
    }

    // CHANGED: Renamed variable to amountInGbp
    fun addExpense(title: String, amountInGbp: Double, targetCurrency: String) {
        viewModelScope.launch {
            val targetRate = _rates.value[targetCurrency] ?: 1.0

            val convertedAmount = convertCurrencyUseCase(
                amount = amountInGbp,
                targetRate = targetRate
            )

            val newExpense = Expense(
                title = title,
                amount = convertedAmount,
                currency = targetCurrency,
                date = System.currentTimeMillis()
            )

            repository.saveExpense(newExpense)
        }
    }
    // --- NEW: Delete function for the UI to call ---
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }
}