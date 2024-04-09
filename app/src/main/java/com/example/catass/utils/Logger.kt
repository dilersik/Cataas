package com.example.catass.utils

interface Logger {
    fun error(tag: String, message: String, throwable: Throwable?)
}