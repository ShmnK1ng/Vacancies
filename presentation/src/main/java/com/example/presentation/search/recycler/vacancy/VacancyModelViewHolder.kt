package com.example.presentation.search.recycler.vacancy

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.VacancyModel
import com.example.presentation.R
import com.example.presentation.databinding.VacancyItemBinding
import com.example.presentation.utils.getOffersText

class VacancyModelViewHolder(
    private val binding: VacancyItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: VacancyModel) {
        if (item.lookingNumber != null) {
            binding.vacancyItemTextViewCountViews.text = getOffersText(item.lookingNumber as Int, binding.vacancyItemTextViewCountViews.context)
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