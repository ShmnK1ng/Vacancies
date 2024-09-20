package com.example.data.remote.dto

import com.squareup.moshi.Json

data class ButtonDto(
    @Json(name = "text")
    val text: String
)