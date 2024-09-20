package com.example.data.remote.dto

import com.squareup.moshi.Json

data class ExperienceDto(
    @Json(name = "previewText")
    val previewText: String,

    @Json(name = "text")
    val text: String
)