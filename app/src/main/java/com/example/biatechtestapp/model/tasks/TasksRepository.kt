package com.example.biatechtestapp.model.tasks

import com.example.biatechtestapp.core.Container
import com.example.biatechtestapp.model.tasks.entities.TaskData
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface TasksRepository {

    suspend fun getTasks(): Flow<Container<List<TaskData>>>

    class TasksRepositoryImpl @Inject constructor(
        private val cacheDataSource: TasksCacheDataSource
    ) : TasksRepository {
        override suspend fun getTasks(): Flow<Container<List<TaskData>>> = flow {
            cacheDataSource.getTasks()
                .catch {
                    emit(Container.Error(it))
                }
                .collect {
                emit(Container.Success(it.map { taskDb -> taskDb.toTaskData() }))
            }

        }
    }
}