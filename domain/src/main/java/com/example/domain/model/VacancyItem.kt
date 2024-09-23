package com.example.domain.model

data class VacancyItem(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: String?,
) : DisplayableItem