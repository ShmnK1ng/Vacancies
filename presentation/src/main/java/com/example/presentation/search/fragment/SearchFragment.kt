package com.example.presentation.search.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.DisplayableItem
import com.example.presentation.R
import com.example.presentation.base.viewBinding
import com.example.presentation.databinding.FragmentSearchBinding
import com.example.presentation.search.viewmodel.SearchViewModel
import com.example.presentation.utils.flowWithStartedLifecycle
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding { FragmentSearchBinding.bind(it) }
    private val viewModel: SearchViewModel by viewModels<SearchViewModel>()
    private var adapter: ListDelegationAdapter<List<DisplayableItem>>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListDelegationAdapter(
            SearchFragmentDelegates.recommendationsDelegate(viewModel::onOfferModelClicked),
            SearchFragmentDelegates.vacanciesShortDelegate(viewModel::onMoreVacanciesButtonClicked)
        )
        binding.searchFragment2RecyclerView.adapter = adapter
        setupDataObserver(adapter)
        setupOnMoreVacanciesButtonClickObserver()
        setOnRecommendationCLickObserver()
        setOnBuckCLickObserver()
    }

    private fun setupDataObserver(adapter: ListDelegationAdapter<List<DisplayableItem>>?) {
        viewModel.data.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach {
                adapter?.apply {
                    if (it.isNotEmpty()) {
                        items = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupOnMoreVacanciesButtonClickObserver() {
        viewModel.isMoreVacanciesButtonClicked.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach {
                if (it.isNotEmpty()) {
                    val newAdapter = ListDelegationAdapter(
                        SearchFragmentDelegates.vacanciesDelegate(viewModel::onBuckButtonClicked)
                    )
                    binding.searchFragment2RecyclerView.adapter = newAdapter
                    newAdapter.items = it
                    newAdapter.notifyDataSetChanged()
                    viewModel.resetClickState()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setOnRecommendationCLickObserver() {
        viewModel.intent.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach { intent ->
                if (intent != null) {
                    startActivity(intent)
                    viewModel.resetClickState()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setOnBuckCLickObserver() {
        viewModel.isBuckButtonClicked.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach {
                if (it.isNotEmpty()) {
                    binding.searchFragment2RecyclerView.adapter = adapter
                    adapter?.items = it
                    adapter?.notifyDataSetChanged()
                    viewModel.resetClickState()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}