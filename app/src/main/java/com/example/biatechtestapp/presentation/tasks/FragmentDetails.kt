package com.example.biatechtestapp.presentation.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.findTopNavController
import com.example.biatechtestapp.core.observe
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.FragmentDetailsBinding
import com.example.biatechtestapp.presentation.tasks.entities.TaskDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {

    private val viewModel by viewModels<DetailsViewModel>()
    private val binding by viewBinding<FragmentDetailsBinding>()
    private val args by navArgs<FragmentDetailsArgs>()
    private var currentTask: TaskDetails? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id

        viewModel.getTaskById(id)

        binding.root.observe(viewLifecycleOwner, viewModel.taskDetail) {
            binding.content.visibility = View.VISIBLE
            if (it.isCurrent) {
                renderCurrentTask(it)
            } else {
                renderDefaultTask(it)
            }
            currentTask = it
        }

        binding.refuseButton.setOnClickListener {
            binding.acceptedContainer.visibility = View.GONE
            binding.defaultContainer.visibility = View.GONE
            viewModel.updateTask(currentTask!!)
        }

        binding.addDocumentsButton.setOnClickListener {
            val direction = FragmentDetailsDirections.actionFragmentDetailsToFragmentDocuments(currentTask!!)
            findTopNavController().navigate(direction)
        }

        binding.acceptTask.setOnClickListener {
            viewModel.updateTask(currentTask!!)
        }
        binding.declineTask.setOnClickListener {
            binding.defaultContainer.visibility = View.GONE
            binding.rulesContainer.isEnabled = false
        }
    }


    private fun renderCurrentTask(task: TaskDetails) {
        with(binding) {
            typeProduct.text = task.typeProduct
            city.text = task.city
            orderDate.text = task.date
            arrivalTime.text = task.time
            addressFrom.text = task.addressFrom
            addressTo.text = task.addressTo
            typeCarcase.text = task.typeCarcase
            orderDetails.text = task.details
            orderParameters.text = task.parameters
            numberTV.text = task.number
            fioTV.text = task.fio
            binding.defaultContainer.visibility = View.GONE
            acceptedContainer.visibility = View.VISIBLE
        }
    }

    private fun renderDefaultTask(task: TaskDetails) {
        with(binding) {
            typeProduct.text = task.typeProduct
            city.text = task.city
            orderDate.text = task.date
            arrivalTime.text = task.time
            addressFrom.text = task.addressFrom
            addressTo.text = task.addressTo
            typeCarcase.text = task.typeCarcase
            orderDetails.text = task.details
            orderParameters.text = task.parameters
            numberTV.text = task.number
            fioTV.text = task.fio
            acceptedContainer.visibility = View.GONE
            defaultContainer.visibility = View.VISIBLE
        }
    }

}