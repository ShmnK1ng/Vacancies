package com.example.presentation.search.fragment

import android.view.View.GONE
import com.example.data.BOOST_RESUME
import com.example.data.NEAR_VACANCIES
import com.example.data.PREVIEW_COUNT
import com.example.data.TEMPORARY_JOB
import com.example.domain.model.DisplayableItem
import com.example.domain.model.OfferItem
import com.example.domain.model.OffersItem
import com.example.domain.model.VacanciesItem
import com.example.domain.model.VacancyItem
import com.example.presentation.R
import com.example.presentation.databinding.ItemOfferBinding
import com.example.presentation.databinding.ItemRecommendationsBinding
import com.example.presentation.databinding.ItemVacanciesBinding
import com.example.presentation.databinding.ItemVacanciesShortBinding
import com.example.presentation.databinding.ItemVacancyBinding
import com.example.presentation.utils.getOffersText
import com.example.presentation.utils.getVacanciesText
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object SearchFragmentDelegates {

    fun recommendationsDelegate(onOfferItemClick: (OfferItem) -> Unit) = adapterDelegateViewBinding<OffersItem, DisplayableItem, ItemRecommendationsBinding>(
        { inflater, container -> ItemRecommendationsBinding.inflate(inflater, container, false) }
    ) {
        binding.itemRecommendationsShortRecyclerView.adapter = ListDelegationAdapter(offerDelegate(onOfferItemClick))

        bind {
            (binding.itemRecommendationsShortRecyclerView.adapter as ListDelegationAdapter<List<OfferItem>>).apply {
                items = item.offers
                notifyDataSetChanged()
            }
        }
    }

        private fun offerDelegate(onOfferItemClick: (OfferItem) -> Unit) = adapterDelegateViewBinding<OfferItem, DisplayableItem, ItemOfferBinding>(
        {inflater, container -> ItemOfferBinding.inflate(inflater, container, false)}
    ) {

        bind {
            binding.fragmentSearchOfferItemLayout.setOnClickListener {
                onOfferItemClick(item)
            }
            val title = binding.offerItemBoostResumeTextView
            val icon = binding.offerItemImageViewIcon
            val button = binding.offerItemUpResumeTextView
            val nearVacanciesId = R.drawable.ic_near_vacancies
            val boostResumeId = R.drawable.ic_level_up_resume
            val temporaryJobId = R.drawable.ic_temporary_job
            val recommendationBackgroundBlue = R.drawable.recommendation_background_blue
            val recommendationBackgroundGreen = R.drawable.recommendation_background_green

            title.text = item.title.trim()

            when (item.id) {
                NEAR_VACANCIES -> {
                    binding.offerItemImageViewIcon.setImageResource(nearVacanciesId)
                    icon.setBackgroundResource(recommendationBackgroundBlue)
                }

                BOOST_RESUME -> {
                    icon.setImageResource(boostResumeId)
                    icon.setBackgroundResource(recommendationBackgroundGreen)
                }

                TEMPORARY_JOB -> {
                    icon.setImageResource(temporaryJobId)
                    icon.setBackgroundResource(recommendationBackgroundGreen)
                }

                else -> {
                    icon.visibility = GONE
                }
            }

            if (item.buttonText != null) {
                title.maxLines = 2
                button.text = item.buttonText
            } else {
                title.maxLines = 3
                button.visibility = GONE
            }
        }
    }

    fun vacanciesShortDelegate(onMoreVacanciesClick: () -> Unit) = adapterDelegateViewBinding<VacanciesItem, DisplayableItem, ItemVacanciesShortBinding>(
        { inflater, container -> ItemVacanciesShortBinding.inflate(inflater, container, false) }
    ) {
        binding.itemVacanciesShortRecyclerView.adapter = ListDelegationAdapter(vacancyDelegate)
        binding.itemVacanciesShortMoreVacanciesButton.setOnClickListener {
            onMoreVacanciesClick()
        }
        bind {
        (binding.itemVacanciesShortRecyclerView.adapter as ListDelegationAdapter<List<VacancyItem>>).apply {
            if (binding.itemVacanciesShortMoreVacanciesButton.visibility != GONE) {
                items = item.vacancies.take(PREVIEW_COUNT)
                binding.itemVacanciesShortMoreVacanciesButton.text = buildString {
                    append(context.resources.getStringArray(R.array.more_vacancies_text)[0])
                    append(" ")
                    append(getVacanciesText(item.vacancies.size, context))
                }
                notifyDataSetChanged()
            }
        }
    }
    }

   fun vacanciesDelegate() = adapterDelegateViewBinding<VacanciesItem, DisplayableItem, ItemVacanciesBinding>(
        { inflater, container -> ItemVacanciesBinding.inflate(inflater, container, false) }
    ) {
        binding.itemVacanciesRecyclerView.adapter = ListDelegationAdapter(vacancyDelegate)
        bind {
            (binding.itemVacanciesRecyclerView.adapter as ListDelegationAdapter<List<VacancyItem>>).apply {
                items = item.vacancies
                binding.itemVacanciesCountVacanciesTextView.text = getVacanciesText(item.vacancies.size, context)
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