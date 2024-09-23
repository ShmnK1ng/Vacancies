package com.example.presentation.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DisplayableItem
import com.example.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel@Inject constructor(
    getFavoritesUseCase: GetFavoritesUseCase,
) : ViewModel() {

    private val _favoritesVacancies = MutableStateFlow<List<DisplayableItem>>(emptyList())
    val favoritesVacancies: StateFlow<List<DisplayableItem>> = _favoritesVacancies

    init {
        getFavoritesUseCase.getFavourites()
            .onEach { _favoritesVacancies.value = it }
            .launchIn(viewModelScope)
    }
}