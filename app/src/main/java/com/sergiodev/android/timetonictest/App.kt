package com.sergiodev.android.timetonictest

import android.app.Application
import com.sergiodev.android.timetonictest.data.local.AppPreferences

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }

}