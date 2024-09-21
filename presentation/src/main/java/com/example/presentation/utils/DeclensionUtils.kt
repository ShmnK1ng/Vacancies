package com.example.presentation.utils

import android.content.Context
import com.example.presentation.R

fun getVacanciesText(count: Int, context: Context): String {
    val vacanciesText = context.resources.getStringArray(R.array.more_vacancies_text)

    val result = when {
        count % 100 in 11..19 -> vacanciesText[3]
        count % 10 == 1 -> vacanciesText[1]
        count % 10 in 2..4 -> vacanciesText[2]
        else -> vacanciesText[3]
    }

    return "${vacanciesText[0]} $count $result"
}

fun getOffersText(count: Int, context: Context): String {
    val offersText = context.resources.getStringArray(R.array.lookingNumber)

    val lastTwoDigits = count % 100
    val lastDigit = count % 10

    val result = when {
        lastTwoDigits in 11..14 -> offersText[1]
        lastDigit == 1 -> offersText[1]
        lastDigit in 2..4 -> offersText[2]
        else -> offersText[1]
    }

    return "${offersText[0]} $count $result"
}