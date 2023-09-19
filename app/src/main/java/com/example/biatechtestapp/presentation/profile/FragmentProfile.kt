package com.example.biatechtestapp.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.findTopNavController
import com.example.biatechtestapp.core.observe
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentProfile: Fragment(R.layout.fragment_profile) {


    private val viewModel by viewModels<ProfileViewModel>()
    private val binding by viewBinding<FragmentProfileBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfile()

        binding.logOutButton.setOnClickListener {
            //viewModel.logout
            findTopNavController().navigate(R.id.action_tabsFragment_to_fragmentLogin)
        }

        binding.root.observe(viewLifecycleOwner,viewModel.profile){
            with(binding){
                content.visibility = View.VISIBLE
                Glide.with(requireContext()).load(it.avatarUrl).circleCrop().into(avatar)

                fioTV.text = it.fio
                roleTV.text = it.role
                numberOfTabel.text = it.numOfTabel.toString()
                mobileNumber.text = it.number
                car.text = it.typeOfCar
                carNumbers.text = it.numbersOfCar
                nationality.text = it.nationality
            }
        }

    }


}