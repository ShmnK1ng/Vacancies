package com.example.data.di

import com.example.data.repository.DataRepositoryImpl
import com.example.domain.repository.DataRepository
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetOffersUseCaseImpl
import com.example.domain.usecase.GetVacanciesUseCase
import com.example.domain.usecase.GetVacanciesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDataRepository(repository: DataRepositoryImpl): DataRepository

    @Binds
    abstract fun bindGetOffersUseCase(getOffersUseCase: GetOffersUseCaseImpl): GetOffersUseCase

    @Binds
    abstract fun bindGetVacanciesUseCase(getVacanciesUseCase: GetVacanciesUseCaseImpl): GetVacanciesUseCase
}