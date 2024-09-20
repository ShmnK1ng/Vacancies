package com.example.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.OfferModel
import com.example.domain.model.VacancyModel
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getVacanciesUseCase: GetVacanciesUseCase,
    getOffersUseCase: GetOffersUseCase,
) : ViewModel() {
    private val _vacancies = MutableStateFlow<List<VacancyModel>>(emptyList())
    val vacancies: StateFlow<List<VacancyModel>> = _vacancies

    private val _offers = MutableStateFlow<List<OfferModel>>(emptyList())
    val offers: StateFlow<List<OfferModel>> = _offers

    init {
       getVacanciesUseCase.getVacancies()
           .onEach { vacanciesList -> _vacancies.value = vacanciesList }
           .launchIn(viewModelScope)
        getOffersUseCase.getOffers()
            .onEach { offersList -> _offers.value = offersList }
            .launchIn(viewModelScope)
    }
}