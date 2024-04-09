package com.example.catass.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catass.model.Cats
import com.example.catass.model.ResultWrapper
import com.example.catass.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val catsRepository: CatsRepository): ViewModel() {

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _cats: MutableStateFlow<Cats?> = MutableStateFlow(null)
    val cats = _cats.asStateFlow()

    fun loadCats() = viewModelScope.launch {
        _loading.value = true
        when (val result = catsRepository.getCats()) {
            is ResultWrapper.Success -> _cats.value = result.data
            is ResultWrapper.Error -> _error.value = result.exception.message
        }
        _loading.value = false
    }

}