package com.example.globalexpenseexchangetracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExpenseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {
    // This connects the DAO to the database
    abstract val expenseDao: ExpenseDao
}