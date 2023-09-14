package com.example.biatechtestapp.model.tasks.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("type_product")
    val typeProduct: String,
    @ColumnInfo("address_from")
    val addressFrom: String,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("time")
    val time: String,
    @ColumnInfo("address_to")
    val addressTo: String,
    @ColumnInfo("details")
    val details: String,
    @ColumnInfo("parameters")
    val parameters: String,
    @ColumnInfo("type_carcase")
    val typeCarcase: String,
    @ColumnInfo("number")
    val number: String,
    @ColumnInfo("fio")
    val fio: String,
    @ColumnInfo("is_current")
    val isCurrent: Boolean,
    @ColumnInfo("is_done")
    val isDone: Boolean
) {

    fun toTaskData() = TaskData(
        id,
        typeProduct,
        addressFrom,
        date,
        time,
        addressTo,
        details,
        parameters,
        typeCarcase,
        number,
        fio,
        isCurrent,
        isDone
    )

}