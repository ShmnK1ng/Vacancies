package com.example.data.remote.dto

import com.squareup.moshi.Json

data class AddressDto(
    @Json(name = "town")
    val town: String,

    @Json(name = "street")
    val street: String,

    @Json(name = "house")
    val house: String
)
