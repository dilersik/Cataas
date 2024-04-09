package com.example.catass.repository

import com.example.catass.model.Cats
import com.example.catass.model.ResultWrapper

interface CatsRepository {
    suspend fun getCats(): ResultWrapper<Cats>
}