package com.example.catass.repository

import android.util.Log
import com.example.catass.model.Cats
import com.example.catass.model.ResultWrapper
import com.example.catass.network.CatsApi
import javax.inject.Inject

class CatsRepositoryImp @Inject constructor(private val catsApi: CatsApi): CatsRepository {

    private var resultCats: Cats = Cats()

    override suspend fun getCats(): ResultWrapper<Cats> = try {
        if (resultCats.isEmpty())
            resultCats = catsApi.getCats()
        ResultWrapper.Success(resultCats)
    } catch (e: Exception) {
        Log.e(TAG, "getAll: ${e.localizedMessage}")
        ResultWrapper.Error(e)
    }

    private companion object {
        private const val TAG = "CatsRepositoryImp"
    }
}