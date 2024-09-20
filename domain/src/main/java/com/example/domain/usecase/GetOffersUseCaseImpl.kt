package com.example.domain.usecase

import com.example.domain.model.DataModel
import com.example.domain.model.OfferModel
import com.example.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOffersUseCaseImpl @Inject constructor(private val repository: DataRepository) : GetOffersUseCase {
    override fun getOffers(): Flow<List<OfferModel>> {
        return repository.getData().map { dataModel: DataModel -> dataModel.offers }
    }
}

interface GetOffersUseCase {
    fun getOffers(): Flow<List<OfferModel>>
}