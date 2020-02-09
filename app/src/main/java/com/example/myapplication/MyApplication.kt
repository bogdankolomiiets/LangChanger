package com.example.myapplication

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(LocalizationHelper.onAttach(context))
    }
}