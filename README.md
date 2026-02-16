# Global Expense & Exchange Tracker 🌍💸

An offline-first Android application designed to track expenses in multiple currencies with real-time conversion. Built to demonstrate production-ready Android development practices including **Clean Architecture**, **MVVM**, and **Test-Driven Development (TDD)**.

## 📱 Features
* **Real-Time Currency Conversion:** Fetches live exchange rates using the public Frankfurter API.
* **Offline-First Support:** Expenses are saved instantly to a local SQLite database, allowing the app to function perfectly without an internet connection.
* **Reactive UI:** Built entirely with Jetpack Compose, observing real-time state changes via Kotlin Flows.
* **Test-Driven:** Core business logic (currency conversion math) was built using TDD principles and JUnit.

## 🛠️ Tech Stack
This project leverages modern Android development tools and libraries:
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
* **Architecture:** Clean Architecture (Domain, Data, Presentation layers) + MVVM
* **Dependency Injection:** [Dagger-Hilt](https://dagger.dev/hilt/)
* **Local Database:** [Room](https://developer.android.com/training/data-storage/room)
* **Networking:** [Retrofit](https://square.github.io/retrofit/) & GSON
* **Asynchronous Programming:** Kotlin Coroutines & Flow
* **Testing:** JUnit4

## 🏗️ Architecture Overview
The app strictly adheres to **Clean Architecture** to ensure separation of concerns, scalability, and testability:
1. **Domain Layer:** The core business logic and models (`Expense`, `ExchangeRate`, `ConvertCurrencyUseCase`). It has absolutely no Android framework dependencies.
2. **Data Layer:** The "engine" of the app. It implements the repository interface and manages data sources (Retrofit for remote API calls, Room for local caching).
3. **Presentation Layer:** The UI, powered by Jetpack Compose and ViewModels. It only interacts with the Domain layer.

## 🚀 Getting Started
To run this project locally:
1. Clone the repository:
   ```bash
   git clone [https://github.com/YourUsername/GlobalExpenseExchangeTracker.git](https://github.com/YourUsername/GlobalExpenseExchangeTracker.git)