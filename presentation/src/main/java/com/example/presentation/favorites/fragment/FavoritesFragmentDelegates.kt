package com.example.presentation.favorites.fragment

import android.view.View.GONE
import com.example.domain.model.DisplayableItem
import com.example.domain.model.VacanciesItem
import com.example.domain.model.VacancyItem
import com.example.presentation.R
import com.example.presentation.databinding.ItemFavoritesBinding
import com.example.presentation.databinding.ItemVacancyBinding
import com.example.presentation.utils.getOffersText
import com.example.presentation.utils.getVacanciesText
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object FavoritesFragmentDelegates {

    fun vacanciesDelegate() = adapterDelegateViewBinding<VacanciesItem, DisplayableItem, ItemFavoritesBinding>(
        { inflater, container -> ItemFavoritesBinding.inflate(inflater, container, false) }
    ) {
        binding.itemFavoritesVacanciesListRecyclerView.adapter = ListDelegationAdapter(vacancyDelegate)

        bind {
            (binding.itemFavoritesVacanciesListRecyclerView.adapter as ListDelegationAdapter<List<VacancyItem>>).apply {
                items = item.vacancies
                binding.itemFavoritesCountVacanciesTextView.text = getVacanciesText(item.vacancies.size, context)
                notifyDataSetChanged()
            }
        }
    }

    private val vacancyDelegate = adapterDelegateViewBinding<VacancyItem, DisplayableItem, ItemVacancyBinding>(
        { inflater, container -> ItemVacancyBinding.inflate(inflater, container, false) }
    ) {
        bind {
            if (item.lookingNumber != null) {
                binding.vacancyItemTextViewCountViews.text = getOffersText(item.lookingNumber as Int, binding.vacancyItemTextViewCountViews.context)
            } else {
                binding.vacancyItemTextViewCountViews.visibility = GONE
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
}