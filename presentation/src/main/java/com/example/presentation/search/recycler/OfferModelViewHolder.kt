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

    fun bind(item: OfferModel) {
       binding.fragmentSearchOfferItemLayout.setOnClickListener {
           onItemClickListener.onClick(item)
       }

        title.text = item.title.trim()

        when (item.id) {
            NEAR_VACANCIES -> {
                icon.setImageResource(R.drawable.ic_near_vacancies)
                icon.setBackgroundResource(R.drawable.recommendation_background_blue)
            }

            BOOST_RESUME -> {
                icon.setImageResource(R.drawable.ic_level_up_resume)
                icon.setBackgroundResource(R.drawable.recommendation_background_green)
            }

            TEMPORARY_JOB -> {
                icon.setImageResource(R.drawable.ic_temporary_job)
                icon.setBackgroundResource(R.drawable.recommendation_background_green)
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