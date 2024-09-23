package com.example.presentation.favorites.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.base.viewBinding
import com.example.presentation.databinding.FragmentFavoritesBinding
import com.example.presentation.favorites.viewmodel.FavoritesViewModel
import com.example.presentation.utils.flowWithStartedLifecycle
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding { FragmentFavoritesBinding.bind(it) }
    private val viewModel by viewModels<FavoritesViewModel>()
    private var adapter = ListDelegationAdapter(
        FavoritesFragmentDelegates.vacanciesDelegate()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchFavoritesRecyclerView.adapter = adapter
        viewModel.favoritesVacancies.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach {
                if(it.isNotEmpty()) {
                    adapter.items = it
                    adapter.notifyDataSetChanged()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
