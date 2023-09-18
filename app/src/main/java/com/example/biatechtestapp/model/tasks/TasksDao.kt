package com.example.biatechtestapp.model.tasks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.biatechtestapp.model.tasks.entities.TaskDb
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): Flow<List<TaskDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskDb: TaskDb)

    @Query("SELECT * FROM tasks WHERE id = :id")
     fun getTaskById(id: Int): Flow<TaskDb>

}