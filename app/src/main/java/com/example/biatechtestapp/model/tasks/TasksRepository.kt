package com.example.biatechtestapp.model.tasks

import android.util.Log
import com.example.biatechtestapp.core.Container
import com.example.biatechtestapp.model.PreferenceStore
import com.example.biatechtestapp.model.tasks.entities.TaskData
import com.example.biatechtestapp.model.tasks.entities.TaskDb
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface TasksRepository {

    suspend fun getTasks(): Flow<Container<List<TaskData>>>

    suspend fun getTaskById(id: Int): Flow<Container<TaskData>>
   suspend  fun updateTask(id: Int)

    class TasksRepositoryImpl @Inject constructor(
        private val cacheDataSource: TasksCacheDataSource,
        private val preferenceStore: PreferenceStore
    ) : TasksRepository {
        override suspend fun getTasks(): Flow<Container<List<TaskData>>> = flow {
            if (preferenceStore.isFirstRun()) {
                cacheDataSource.inflateTable()
                preferenceStore.setFlag(false)
            }
            cacheDataSource.getTasks()
                .catch {
                    emit(Container.Error(it))
                }
                .collect {
                    Log.d("TAG", "rep ${it.size}")
                    emit(Container.Success(it.map { taskDb -> taskDb.toTaskData() }))
                }
        }

        override suspend fun updateTask(id: Int) {
            Log.d("TAG"," invoked and entity is $id")
            var res: TaskDb? = null
            cacheDataSource.getTaskById(id).collectLatest {
                Log.d("TAG"," invoked and entity in collect is $it")
                res = it
                val newTask = it.copy(isCurrent = true)
                cacheDataSource.updateTask(newTask!!)
            }


        }

        override suspend fun getTaskById(id: Int): Flow<Container<TaskData>> = flow {
            cacheDataSource.getTaskById(id).catch {
                emit(Container.Error(it))
            }.collect {
                emit(Container.Success(it.toTaskData()))
            }
        }
    }
}