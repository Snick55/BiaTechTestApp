package com.example.biatechtestapp.presentation.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.di.IoDispatcher
import com.example.biatechtestapp.di.MainDispatcher
import com.example.biatechtestapp.model.chat.ChatRepository
import com.example.biatechtestapp.presentation.chat.entities.ChatUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    @MainDispatcher private val dispatcherMain: CoroutineDispatcher,
    private val repository: ChatRepository
): ViewModel() {

    private val _chats = MutableLiveData<List<ChatUiItem>>()
    val chats: LiveData<List<ChatUiItem>> = _chats

    fun getChats() = viewModelScope.launch(dispatcherIo) {
        repository.getChats().collect{
            withContext(dispatcherMain){
                _chats.value = it.map {
                    ChatUiItem(it.id,it.avatarUrl,it.name,it.lastMessage,it.timeOfLastMessage,it.countOfMessage)
                }
            }
        }

    }

}