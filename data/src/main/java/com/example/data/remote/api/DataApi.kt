package com.example.data.remote.api

import com.example.data.remote.dto.DataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi {
    @GET("/u/0/uc")
    suspend fun getData(
        @Query("id") id: String,
        @Query("export") export: String = "download"
    ): DataDto
}