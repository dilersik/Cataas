package com.example.catass.utils

import android.util.Log

class AndroidLogger : Logger {
    override fun error(tag: String, message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}