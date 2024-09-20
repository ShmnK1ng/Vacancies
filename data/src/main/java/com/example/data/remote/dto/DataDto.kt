package com.example.data.remote.dto

import com.squareup.moshi.Json

data class DataDto(
    @Json(name = "offers")
    val offers: List<OfferDto>,

    @Json(name = "vacancies")
    val vacancies: List<VacancyDto>
)