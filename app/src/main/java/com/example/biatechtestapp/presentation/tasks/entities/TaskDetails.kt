package com.example.biatechtestapp.presentation.tasks.entities

data class TaskDetails(
    val id: Int,
    val typeProduct:String,
    val addressFrom: String,
    val date:String,
    val time: String,
    val addressTo: String,
    val details: String,
    val parameters: String,
    val typeCarcase: String,
    val number: String,
    val fio: String,
    val isCurrent: Boolean,
    val city: String
): java.io.Serializable