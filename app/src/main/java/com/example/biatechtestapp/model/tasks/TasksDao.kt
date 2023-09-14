package com.example.biatechtestapp.model.tasks

import androidx.room.Dao
import com.example.biatechtestapp.model.tasks.entities.TaskDb
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    fun getTasks(): Flow<List<TaskDb>>

}