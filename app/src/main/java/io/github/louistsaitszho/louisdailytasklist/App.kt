package io.github.louistsaitszho.louisdailytasklist

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceLocator.setApplicationContext(this)
    }
}