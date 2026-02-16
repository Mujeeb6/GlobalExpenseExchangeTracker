package com.example.globalexpenseexchangetracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.globalexpenseexchangetracker.domain.model.Expense

// @Entity tells Room to create a database table for this class
@Entity(tableName = "expenses_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val currency: String,
    val date: Long
) {
    // Mapper to convert database data back to our clean Domain model
    fun toExpense(): Expense {
        return Expense(
            id = id,
            title = title,
            amount = amount,
            currency = currency,
            date = date
        )
    }
}