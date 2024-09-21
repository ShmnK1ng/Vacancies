package com.example.presentation.search.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.domain.model.OfferModel
import com.example.presentation.databinding.FragmentSearchBinding
import com.example.presentation.flowWithStartedLifecycle
import com.example.presentation.search.recycler.OfferListAdapter
import com.example.presentation.search.recycler.OnItemClickListener
import com.example.presentation.search.viewmodel.SearchViewModel
import com.example.data.FRAGMENT_SEARCH_SPAN_COUNT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIntentObserver()
        setupOfferListAdapter()
    }

    private fun setupIntentObserver() {
        viewModel.intent.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach { intent ->
                if (intent != null)
                    startActivity(intent)
                viewModel.resetClickState()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupOfferListAdapter() {
        val onItemClickListener = object : OnItemClickListener {
            override fun onClick(offerModel: OfferModel) {
                viewModel.onOfferModelClicked(offerModel)
            }
        }
        val offerListAdapter = OfferListAdapter(onItemClickListener)
        binding?.let { binding ->
            binding.fragmentSearchRecyclerViewOfferList.adapter = offerListAdapter
            binding.fragmentSearchRecyclerViewOfferList.layoutManager = StaggeredGridLayoutManager(
                FRAGMENT_SEARCH_SPAN_COUNT, StaggeredGridLayoutManager.HORIZONTAL
            )
        }
        viewModel.offers.flowWithStartedLifecycle(viewLifecycleOwner)
            .onEach {
                if (it.isNotEmpty()) {
                    binding?.fragmentSearchOfferProgressBar?.visibility = GONE
                    offerListAdapter.submitList(it)
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}