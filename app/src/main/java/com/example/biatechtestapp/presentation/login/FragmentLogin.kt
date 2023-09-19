package com.example.biatechtestapp.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLogin: Fragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signIn.setOnClickListener {
            viewModel.setAuthorize(true)
            findNavController().navigate(R.id.action_fragmentLogin_to_tabsFragment, null, navOptions {
                popUpTo(R.id.fragmentLogin){
                    inclusive = true
                }
            })
        }
    }

}