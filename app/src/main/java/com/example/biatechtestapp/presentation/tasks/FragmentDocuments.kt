package com.example.biatechtestapp.presentation.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.findTopNavController
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.FragmentDocumentsBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDocuments: Fragment(R.layout.fragment_documents) {

    private val viewModel by viewModels<DocumentsViewModel>()
    private val binding by viewBinding<FragmentDocumentsBinding>()
    private val args by navArgs<FragmentDocumentsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.completeTaskButton.setOnClickListener {
            viewModel.updateTask(args.taskDetails)
            findTopNavController().navigate(R.id.action_fragmentDocuments_to_tabsFragment,null,
                navOptions {
                    popUpTo(R.id.fragmentDocuments){
                        inclusive = true
                    }
                    popUpTo(R.id.fragmentDetails){
                        inclusive = true
                    }
                    popUpTo(R.id.tabsFragment){
                        inclusive = true
                    }
                })
        }

    }

}