package com.example.catass.ui.views

import com.example.catass.MainDispatcherRule
import com.example.catass.model.CatItem
import com.example.catass.model.Cats
import com.example.catass.model.ResultWrapper
import com.example.catass.repository.CatsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var catsRepository: CatsRepository
    private lateinit var catsViewModel: CatsViewModel

    @Before
    fun setUp() {
        catsRepository = mock()
        catsViewModel = CatsViewModel(catsRepository)
    }

    @Test
    fun `loadCats success`(): Unit = runTest {
        // Mock data
        val cats = Cats(listOf(CatItem("1", "Fluffy", 1, emptyList())))
        whenever(catsRepository.getCats()).thenReturn(ResultWrapper.Success(cats))

        // Call the function under test
        catsViewModel.loadCats()

        // Verify loading state
        assertEquals(false, catsViewModel.loading.first())

        // Verify cats data
        assertEquals(cats, catsViewModel.cats.first())

        // Verify error state
        assertEquals(null, catsViewModel.error.first())

        // Verify repository method is called
        verify(catsRepository).getCats()

        // Verify loading state after completion
        assertEquals(false, catsViewModel.loading.first())
    }

    @Test
    fun `loadCats error`(): Unit = runTest {
        // Mock error
        val errorMessage = "Error loading cats"
        whenever(catsRepository.getCats()).thenReturn(ResultWrapper.Error(Exception(errorMessage)))

        // Call the function under test
        catsViewModel.loadCats()

        // Verify loading state
        assertEquals(false, catsViewModel.loading.first())

        // Verify error message
        assertEquals(errorMessage, catsViewModel.error.first())

        // Verify cats data
        assertEquals(null, catsViewModel.cats.first())

        // Verify repository method is called
        verify(catsRepository).getCats()

        // Verify loading state after completion
        assertEquals(false, catsViewModel.loading.first())
    }
}