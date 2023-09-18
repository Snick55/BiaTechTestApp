package com.example.biatechtestapp.model.chat.entities

data class ChatData(
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val lastMessage: String,
    val timeOfLastMessage: String,
    val countOfMessage: Int
)
