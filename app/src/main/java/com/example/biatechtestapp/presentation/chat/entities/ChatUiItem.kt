package com.example.biatechtestapp.presentation.chat.entities

data class ChatUiItem(
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val lastMessage: String,
    val timeOfLastMessage: String,
    val countOfMessage: Int
)
