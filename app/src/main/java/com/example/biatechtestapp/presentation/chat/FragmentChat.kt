package com.example.biatechtestapp.presentation.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.biatechtestapp.R
import com.example.biatechtestapp.core.viewBinding
import com.example.biatechtestapp.databinding.FragmentChatBinding
import com.example.biatechtestapp.databinding.FragmentDetailsBinding
import com.example.biatechtestapp.presentation.tasks.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentChat: Fragment(R.layout.fragment_chat) {


    private val viewModel by viewModels<ChatViewModel>()
    private val binding by viewBinding<FragmentChatBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getChats()
        val adapter = ChatAdapter()

        with(binding.chatsRV){
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
            this.adapter = adapter
        }

        viewModel.chats.observe(viewLifecycleOwner){
            adapter.setUpAdapter(it)
        }

    }


}