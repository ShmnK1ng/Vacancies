package com.example.data

import com.example.data.remote.dto.DataDto
import com.example.domain.model.DataModel
import com.example.domain.model.OfferModel
import com.example.domain.model.VacancyModel

fun DataDto.toDataModel(): DataModel {
    return DataModel(
        offers = this.offers.map { offerDto ->
            OfferModel(
                id = offerDto.id,
                title = offerDto.title,
                link = offerDto.link,
                buttonText = offerDto.button?.text
            )
        },
        vacancies = this.vacancies.map { vacancyDto ->
            VacancyModel(
                id = vacancyDto.id,
                lookingNumber = vacancyDto.lookingNumber,
                title = vacancyDto.title,
                address = vacancyDto.address.town,
                company = vacancyDto.company,
                experience = vacancyDto.experience.previewText,
                publishedDate = vacancyDto.publishedDate,
                isFavorite = vacancyDto.isFavorite,
                salary = vacancyDto.salary.shortSalary,
            )
        }
    )
}