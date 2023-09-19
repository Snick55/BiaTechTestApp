package com.example.biatechtestapp.presentation.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.findTopNavController
import com.example.biatechtestapp.core.observe
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.FragmentTasksBinding
import com.example.biatechtestapp.presentation.TabsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentTasks : Fragment(R.layout.fragment_tasks) {

    private val viewModel by viewModels<TasksViewModel>()
    private val binding by viewBinding<FragmentTasksBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TasksAdapter( onTaskPressed = {
            val direction = TabsFragmentDirections.actionTabsFragmentToFragmentDetails(it)
            findTopNavController().navigate(direction)
        })
        viewModel.getTasks()

        with(binding.tasksRV){
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
            this.adapter = adapter
        }

        binding.root.observe(viewLifecycleOwner,viewModel.tasks){
            adapter.setList(it)
        }

    }


}