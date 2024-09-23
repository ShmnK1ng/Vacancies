package com.example.domain.usecase

import com.example.domain.model.DataModel
import com.example.domain.model.DisplayableItem
import com.example.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDataUseCaseImpl @Inject constructor(private val repository: DataRepository) : GetDataUseCase {
    override fun getOffers(): Flow<List<DisplayableItem>> {
        return repository.getData().map { dataModel: DataModel -> listOf(dataModel.offers, dataModel.vacancies) }
    }
}

interface GetDataUseCase {
    fun getOffers(): Flow<List<DisplayableItem>>
}