package com.example.domain.usecase

import com.example.domain.model.DataModel
import com.example.domain.model.VacancyModel
import com.example.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVacanciesUseCaseImpl @Inject constructor(private val repository: DataRepository) : GetVacanciesUseCase {
    override fun getVacancies(): Flow<List<VacancyModel>> {
        return repository.getData().map { dataModel: DataModel -> dataModel.vacancies }
    }
}

interface GetVacanciesUseCase {
    fun getVacancies(): Flow<List<VacancyModel>>
}