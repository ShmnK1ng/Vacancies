package com.example.data

import com.example.data.remote.dto.DataDto
import com.example.domain.model.DataModel
import com.example.domain.model.OfferItem
import com.example.domain.model.OffersItem
import com.example.domain.model.VacanciesItem
import com.example.domain.model.VacancyItem
import java.time.LocalDate
import java.util.Locale
import java.time.format.DateTimeFormatter

fun DataDto.toDataModel(): DataModel {
    return DataModel(
        offers = OffersItem(this.offers.map { offerDto ->
            OfferItem(
                id = offerDto.id,
                title = offerDto.title,
                link = offerDto.link,
                buttonText = offerDto.button?.text
            )
        }),
        vacancies = VacanciesItem(this.vacancies.map { vacancyDto ->
            val date = LocalDate.parse(vacancyDto.publishedDate)
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
            val formattedDate = date.format(formatter)
            VacancyItem(
                id = vacancyDto.id,
                lookingNumber = vacancyDto.lookingNumber,
                title = vacancyDto.title,
                address = vacancyDto.address.town,
                company = vacancyDto.company,
                experience = vacancyDto.experience.previewText,
                publishedDate = formattedDate,
                isFavorite = vacancyDto.isFavorite,
                salary = vacancyDto.salary.shortSalary,
            )
        }
    ))
}