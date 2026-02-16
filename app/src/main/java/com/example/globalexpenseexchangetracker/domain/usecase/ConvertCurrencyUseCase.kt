package com.example.globalexpenseexchangetracker.domain.usecase

class ConvertCurrencyUseCase {
    operator fun invoke(amount: Double, targetRate: Double, baseRate: Double = 1.0): Double {
        if (baseRate == 0.0) return 0.0
        return amount * (targetRate / baseRate)
    }
}