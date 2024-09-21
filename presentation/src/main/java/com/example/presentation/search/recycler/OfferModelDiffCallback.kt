package com.example.presentation.search.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.OfferModel

class OfferModelDiffCallback: DiffUtil.ItemCallback<OfferModel>() {
    override fun areItemsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: OfferModel, newItem: OfferModel): Boolean {
        return oldItem == newItem
    }
}