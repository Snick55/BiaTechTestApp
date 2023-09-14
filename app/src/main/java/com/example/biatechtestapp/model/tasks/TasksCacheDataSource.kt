package com.example.biatechtestapp.model.tasks

import com.example.biatechtestapp.model.tasks.entities.TaskDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TasksCacheDataSource {

 suspend fun getTasks(): Flow<List<TaskDb>>


  class TasksCacheDataSourceImpl @Inject constructor(
      private val dao: TasksDao
  ): TasksCacheDataSource{

      override suspend fun getTasks(): Flow<List<TaskDb>> {
          delay(2000)
        return  dao.getTasks()
      }
  }
}