package com.example.globalexpenseexchangetracker

import com.example.globalexpenseexchangetracker.domain.usecase.ConvertCurrencyUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertCurrencyUseCaseTest {

    private val convertCurrencyUseCase = ConvertCurrencyUseCase()

    @Test
    fun `when amount is 100 and target rate is 0_8, return 80`() {
        val result = convertCurrencyUseCase(amount = 100.0, targetRate = 0.8)
        assertEquals(80.0, result, 0.001)
    }
}