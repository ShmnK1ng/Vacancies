package com.example.data.di

import com.example.data.repository.DataRepositoryImpl
import com.example.domain.repository.DataRepository
import com.example.domain.usecase.GetDataUseCase
import com.example.domain.usecase.GetDataUseCaseImpl
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
    abstract fun bindGetDataUseCase(getDataUseCase: GetDataUseCaseImpl): GetDataUseCase
}