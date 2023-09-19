package com.example.biatechtestapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.TabsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsFragment: Fragment(R.layout.tabs_fragment) {

    private val binding by viewBinding<TabsFragmentBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavView = binding.bottomNavigationView
        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(bottomNavView,navController)

    }



}