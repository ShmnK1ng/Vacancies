package com.example.data.remote.dto

import com.squareup.moshi.Json

data class SalaryDto(
    @Json(name = "full")
    val full: String,

    @Json(name = "short")
    val shortSalary: String? = null
)
