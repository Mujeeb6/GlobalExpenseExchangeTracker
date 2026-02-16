package com.example.globalexpenseexchangetracker.di

import android.app.Application
import androidx.room.Room
import com.example.globalexpenseexchangetracker.data.local.ExpenseDatabase
import com.example.globalexpenseexchangetracker.data.remote.ExchangeRateApi
import com.example.globalexpenseexchangetracker.data.repository.CurrencyRepositoryImpl
import com.example.globalexpenseexchangetracker.domain.repository.CurrencyRepository
import com.example.globalexpenseexchangetracker.domain.usecase.ConvertCurrencyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 1. Tell Hilt how to build Retrofit (The Internet API)
    @Provides
    @Singleton
    fun provideExchangeRateApi(): ExchangeRateApi {
        return Retrofit.Builder()
            // We use Frankfurter, a free, public currency API with no key required
            .baseUrl("https://api.frankfurter.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRateApi::class.java)
    }

    // 2. Tell Hilt how to build the Room Database
    @Provides
    @Singleton
    fun provideExpenseDatabase(app: Application): ExpenseDatabase {
        return Room.databaseBuilder(
            app,
            ExpenseDatabase::class.java,
            "expense_db"
        ).build()
    }

    // 3. Tell Hilt how to build the Repository (Glue Data and Domain together)
    @Provides
    @Singleton
    fun provideCurrencyRepository(
        api: ExchangeRateApi,
        db: ExpenseDatabase
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(api, db.expenseDao)
    }

    // 4. Tell Hilt how to build our Math Logic Use Case
    @Provides
    @Singleton
    fun provideConvertCurrencyUseCase(): ConvertCurrencyUseCase {
        return ConvertCurrencyUseCase()
    }
}