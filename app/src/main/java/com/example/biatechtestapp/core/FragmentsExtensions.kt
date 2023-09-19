package com.example.biatechtestapp.core

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.biatechtestapp.R


fun Fragment.findTopNavController(): NavController{
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}