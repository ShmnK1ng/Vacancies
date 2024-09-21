package com.example.presentation.search.recycler.offer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.OfferModel
import com.example.presentation.databinding.OfferItemBinding

class OfferListAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<OfferModel, OfferModelViewHolder>(OfferModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferModelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OfferItemBinding.inflate(layoutInflater, parent, false)
        return OfferModelViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: OfferModelViewHolder, position: Int) {
        val offerModel = getItem(position)
        holder.bind(offerModel)
    }
}

interface OnItemClickListener {
    fun onClick(offerModel: OfferModel)
}