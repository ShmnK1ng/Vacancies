package com.example.presentation.search.recycler.vacancy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.VacancyModel
import com.example.presentation.databinding.VacancyItemBinding

class VacancyListAdapter : ListAdapter<VacancyModel, VacancyModelViewHolder>(
    VacancyModelDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyModelViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VacancyItemBinding.inflate(layoutInflater, parent, false)
        return VacancyModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacancyModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}