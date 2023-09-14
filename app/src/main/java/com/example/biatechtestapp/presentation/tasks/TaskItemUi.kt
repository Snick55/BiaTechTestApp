package com.example.biatechtestapp.presentation.tasks


sealed class TaskItemUi(val coreId:Int){

    data class Default(
        val id: Int,
        val typeProduct:String,
        val addressFrom: String,
        val date:String,
        val addressTo: String,
        val details: String,
        val parameters: String
    ):TaskItemUi(id)

    data class Current(
        val id: Int,
        val typeProduct:String,
        val addressFrom: String,
        val date:String,
        val addressTo: String,
        val details: String,
        val parameters: String
    ):TaskItemUi(id)

    data class Done(
        val id: Int,
        val typeProduct:String,
        val addressFrom: String,
        val date:String,
        val addressTo: String,
    ):TaskItemUi(id)

}


