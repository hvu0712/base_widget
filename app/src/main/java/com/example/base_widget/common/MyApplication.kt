package com.example.base_widget.common

import android.app.Application
import com.example.base_widget.database.AppDatabase


class MyApplication : Application() {
    private var appDb: AppDatabase? = null


    override fun onCreate() {
        super.onCreate()
        SharePrefUtils.init(this)
        appDb = AppDatabase.getInstance(baseContext)

    }
}