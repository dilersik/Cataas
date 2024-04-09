package com.example.catass.repository

import com.example.catass.model.CatItem
import com.example.catass.model.Cats
import com.example.catass.model.ResultWrapper
import com.example.catass.network.CatsApi
import com.example.catass.utils.Logger
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CatsRepositoryImpTest {

    private lateinit var catsApi: CatsApi
    private lateinit var catsRepository: CatsRepositoryImp
    private lateinit var logger: Logger

    @Before
    fun setUp() {
        catsApi = mock()
        logger = mock()
        catsRepository = CatsRepositoryImp(catsApi, logger)
    }

    @Test
    fun `getCats success`(): Unit = runTest {
        // Mock response
        val cats = Cats(listOf(CatItem("1", "Fluffy", 1, emptyList())))
        whenever(catsApi.getCats()).thenReturn(cats)

        // Call the function under test
        val result = catsRepository.getCats()

        // Verify that the function returns the expected result
        assert(result is ResultWrapper.Success)
        assertEquals(cats, (result as ResultWrapper.Success).data)

        // Verify that API is called
        verify(catsApi).getCats()
    }

    @Test
    fun `getCats error`(): Unit = runTest {
        // Mock exception
        val exception = Exception("API error")

        // Instead of thenThrow(), use thenAnswer() to throw the exception
        whenever(catsApi.getCats()).thenAnswer { throw exception }

        // Call the function under test
        val result = catsRepository.getCats()

        // Verify that the function returns the expected result
        assert(result is ResultWrapper.Error)
        assertEquals(exception, (result as ResultWrapper.Error).exception)

        // Verify that API is called
        verify(catsApi).getCats()
    }

}