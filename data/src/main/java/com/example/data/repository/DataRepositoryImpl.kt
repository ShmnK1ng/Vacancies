package com.example.data.repository

import android.util.Log
import com.example.data.ID
import com.example.data.remote.api.DataApi
import com.example.data.toDataModel
import com.example.domain.model.DataModel
import com.example.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val api: DataApi
) : DataRepository {

    override fun getData(): Flow<DataModel> = flow {
        try {
            val response = api.getData(ID)
            emit(response.toDataModel())
        } catch (e: Exception) {
            Log.e("Error", "Failed to fetch data: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}