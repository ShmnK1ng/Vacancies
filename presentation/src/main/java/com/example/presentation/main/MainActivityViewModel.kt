package com.example.presentation.main

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel@Inject constructor(
    getDataUseCase: GetDataUseCase,
) : ViewModel() {

}