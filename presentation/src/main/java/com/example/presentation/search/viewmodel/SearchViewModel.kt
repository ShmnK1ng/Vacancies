package com.example.presentation.search.viewmodel

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DisplayableItem
import com.example.domain.model.OfferItem
import com.example.domain.model.VacanciesItem
import com.example.domain.usecase.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getDataUseCase: GetDataUseCase,
) : ViewModel() {

    private val _data = MutableStateFlow<List<DisplayableItem>>(emptyList())
    val data: StateFlow<List<DisplayableItem>> = _data

    private val _intent = MutableStateFlow<Intent?>(null)
    val intent: StateFlow<Intent?> = _intent

    private val _isMoreVacanciesButtonClicked = MutableStateFlow<List<DisplayableItem>>(emptyList())
    val isMoreVacanciesButtonClicked: StateFlow<List<DisplayableItem>> = _isMoreVacanciesButtonClicked

    private val _isBuckButtonClicked = MutableStateFlow<List<DisplayableItem>>(emptyList())
    val isBuckButtonClicked: StateFlow<List<DisplayableItem>> = _isBuckButtonClicked

    init {
        getDataUseCase.getOffers()
            .onEach { data -> _data.value = data }
            .launchIn(viewModelScope)
    }

    fun onOfferModelClicked(offerItem: OfferItem) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(offerItem.link)
        }
        _intent.value = intent
    }

    fun resetClickState() {
        _intent.value = null
        _isBuckButtonClicked.value = emptyList()
    }

    fun onMoreVacanciesButtonClicked() {
        _isMoreVacanciesButtonClicked.value = _data.value.filterIsInstance<VacanciesItem>()
    }

    fun onBuckButtonClicked() {
        _isBuckButtonClicked.value = _data.value
    }
}