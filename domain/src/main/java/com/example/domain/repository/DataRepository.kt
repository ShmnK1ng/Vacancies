package com.example.domain.repository

import com.example.domain.model.DataModel
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getData(): Flow<DataModel>
}