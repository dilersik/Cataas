package com.example.catass.model

sealed class ResultWrapper<T> {
    class Error<T>(val exception: Exception): ResultWrapper<T>()
    class Success<T>(val data: T): ResultWrapper<T>()
}