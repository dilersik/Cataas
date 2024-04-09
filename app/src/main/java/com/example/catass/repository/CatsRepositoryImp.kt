package com.example.catass.repository

import com.example.catass.model.Cats
import com.example.catass.model.ResultWrapper
import com.example.catass.network.CatsApi
import com.example.catass.utils.Logger
import javax.inject.Inject

class CatsRepositoryImp @Inject constructor(
    private val catsApi: CatsApi,
    private val logger: Logger
) : CatsRepository {

    private var resultCats: Cats = Cats()

    override suspend fun getCats(): ResultWrapper<Cats> = try {
        if (resultCats.isEmpty())
            resultCats = catsApi.getCats()
        ResultWrapper.Success(resultCats)
    } catch (e: Exception) {
        logger.error(TAG, "getAll: ${e.localizedMessage}", e)
        ResultWrapper.Error(e)
    }

    private companion object {
        private const val TAG = "CatsRepositoryImp"
    }
}