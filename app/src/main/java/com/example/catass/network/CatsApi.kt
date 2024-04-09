package com.example.catass.network

import com.example.catass.model.Cats
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface CatsApi {

    @GET("cats")
    suspend fun getCats(): Cats
}