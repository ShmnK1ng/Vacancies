package com.example.presentation.search.recycler

import android.view.View.GONE
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.OfferModel
import com.example.presentation.R
import com.example.presentation.databinding.OfferItemBinding
import com.example.data.BOOST_RESUME
import com.example.data.NEAR_VACANCIES
import com.example.data.TEMPORARY_JOB

class OfferModelViewHolder(
    private val binding: OfferItemBinding,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val title = binding.offerItemBoostResumeTextView
    private val icon = binding.offerItemImageViewIcon
    private val button = binding.offerItemUpResumeTextView
    private val nearVacanciesId = R.drawable.ic_near_vacancies
    private val boostResumeId = R.drawable.ic_level_up_resume
    private val temporaryJobId = R.drawable.ic_temporary_job
    private val recommendationBackgroundBlue = R.drawable.recommendation_background_blue
    private val recommendationBackgroundGreen = R.drawable.recommendation_background_green

    fun bind(item: OfferModel) {
       binding.fragmentSearchOfferItemLayout.setOnClickListener {
           onItemClickListener.onClick(item)
       }

        title.text = item.title.trim()

        when (item.id) {
            NEAR_VACANCIES -> {
                icon.setImageResource(nearVacanciesId)
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