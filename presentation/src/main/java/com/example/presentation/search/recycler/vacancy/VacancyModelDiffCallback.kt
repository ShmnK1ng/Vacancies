package com.example.presentation.search.recycler.vacancy

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.VacancyModel

class VacancyModelDiffCallback: DiffUtil.ItemCallback<VacancyModel>() {
    override fun areItemsTheSame(oldItem: VacancyModel, newItem: VacancyModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VacancyModel, newItem: VacancyModel): Boolean {
        return oldItem == newItem
    }
}