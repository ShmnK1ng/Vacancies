package com.example.data.remote.dto

import com.squareup.moshi.Json

data class OfferDto(
    @Json(name = "id")
    val id: String? = null,

    @Json(name = "title")
    val title: String,

    @Json(name = "link")
    val link: String,

    @Json(name = "button")
    val button: ButtonDto? = null
)