package com.example.presentation.search.recycler.vacancy

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.VacancyModel
import com.example.presentation.R
import com.example.presentation.databinding.VacancyItemBinding

class VacancyModelViewHolder(
    private val binding: VacancyItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: VacancyModel) {
        if (item.lookingNumber != null) {
            val lookingNumber = item.lookingNumber as Int
            val lastTwoDigits = lookingNumber % 100
            val lastDigit = lookingNumber % 10
            binding.vacancyItemTextViewCountViews.text = buildString {
                append(binding.vacancyItemVacancyNameTextView.context.resources.getStringArray(R.array.lookingNumber)[0])
                append(" ")
                append(item.lookingNumber)
                append(" ")
                if (lastTwoDigits in 11..14) {
                    append(binding.vacancyItemVacancyNameTextView.context.resources.getStringArray(R.array.lookingNumber)[1])
                } else {
                    when (lastDigit) {
                        1 -> {
                            append(binding.vacancyItemVacancyNameTextView.context.resources.getStringArray(R.array.lookingNumber)[1])
                        }
                        in 2..4 -> {
                            append(binding.vacancyItemVacancyNameTextView.context.resources.getStringArray(R.array.lookingNumber)[2])
                        }
                        else -> {
                            append(binding.vacancyItemVacancyNameTextView.context.resources.getStringArray(R.array.lookingNumber)[1])
                        }
                    }
                }
            }
        } else {
            binding.vacancyItemTextViewCountViews.visibility = View.GONE
        }
        binding.vacancyItemVacancyNameTextView.text = item.title
        binding.vacancyItemCityTextView.text = item.address
        binding.vacancyItemCompanyNameTextView.text = item.company
        binding.vacancyItemExperienceTextView.text = item.experience
        binding.vacancyItemDateTextView.text = buildString {
            append(binding.vacancyItemDateTextView.context.resources.getString(R.string.published))
            append(" ")
            append(item.publishedDate)
        }
        if (item.isFavorite) {
            binding.vacancyItemImageButtonFavorites.setImageResource(R.drawable.ic_favorites_active)
        } else {
            binding.vacancyItemImageButtonFavorites.setImageResource(R.drawable.ic_favorites)
        }
    }
}