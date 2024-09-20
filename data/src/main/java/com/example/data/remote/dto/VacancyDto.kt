package com.example.data.remote.dto

import com.squareup.moshi.Json

data class VacancyDto(
    @Json(name = "id")
    val id: String,

    @Json(name = "lookingNumber")
    val lookingNumber: Int? = null,

    @Json(name = "title")
    val title: String,

    @Json(name = "address")
    val address: AddressDto,

    @Json(name = "company")
    val company: String,

    @Json(name = "experience")
    val experience: ExperienceDto,

    @Json(name = "publishedDate")
    val publishedDate: String,

    @Json(name = "isFavorite")
    val isFavorite: Boolean,

    @Json(name = "salary")
    val salary: SalaryDto,

    @Json(name = "schedules")
    val schedules: List<String>,

    @Json(name = "appliedNumber")
    val appliedNumber: Int? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "responsibilities")
    val responsibilities: String,

    @Json(name = "questions")
    val questions: List<String>
)