package com.example.domain.usecase

import com.example.domain.model.DataModel
import com.example.domain.model.DisplayableItem
import com.example.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(private val repository: DataRepository) : GetFavoritesUseCase {
    override fun getFavourites(): Flow<List<DisplayableItem>> {
        return repository.getData().map { dataModel: DataModel ->
            listOf(dataModel.vacancies).map { vacanciesItem ->
                val favourites = vacanciesItem.vacancies.filter { it.isFavorite }
                vacanciesItem.copy(vacancies = favourites)
            }
        }
    }
}

interface GetFavoritesUseCase {
    fun getFavourites(): Flow<List<DisplayableItem>>
}