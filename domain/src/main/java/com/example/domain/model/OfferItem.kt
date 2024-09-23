package com.example.domain.model

data class OfferItem(
    val id: String?,
    val title: String,
    val link: String,
    val buttonText: String?
) : DisplayableItem