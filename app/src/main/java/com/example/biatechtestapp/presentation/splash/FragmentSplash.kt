package com.example.biatechtestapp.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.biatechtestapp.MainActivity
import com.example.biatechtestapp.MainActivityArgs
import com.example.biatechtestapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSplash: Fragment(R.layout.fragment_splash) {


    private val viewModel by viewModels<SplashViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isSignedIn()
        viewModel.isSignedIn.observe(viewLifecycleOwner){
            launchMain(it)
        }

    }

    private fun launchMain(isSignedIn: Boolean){
        val intent = Intent(requireContext(),MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TASK)

        intent.putExtra("isSignedIn" , isSignedIn)
        startActivity(intent)
    }

}