package com.example.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.domain.model.VacanciesItem
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.utils.flowWithStartedLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializeNavController()
        setupBottomNavigationView()
        countOfVacancies()
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.activityMainBottomNavigationView.setupWithNavController(navController)
    }

    private fun setupBottomNavigationView() {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.activityMainBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                R.id.favoritesFragment -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }

                else -> {
                    navController.navigate(R.id.stubFragment)
                    true
                }
            }
        }
    }

    private fun countOfVacancies() {
        viewModel.favoritesVacancies.flowWithStartedLifecycle(this)
            .onEach {
                val vacanciesList = it as? List<VacanciesItem>
                if (!vacanciesList.isNullOrEmpty()) {
                    binding.activityMainBottomNavigationView.getOrCreateBadge(R.id.favoritesFragment).number = vacanciesList[0].vacancies.size
                } else {
                    binding.activityMainBottomNavigationView.removeBadge(R.id.favoritesFragment)
                }
            }
            .launchIn(lifecycleScope)
    }
}