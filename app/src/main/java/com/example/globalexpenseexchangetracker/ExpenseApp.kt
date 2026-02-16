package com.example.globalexpenseexchangetracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// This tiny annotation triggers all the Hilt code generation!
@HiltAndroidApp
class ExpenseApp : Application()