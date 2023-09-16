package com.example.biatechtestapp.presentation.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.core.LiveContainer
import com.example.biatechtestapp.core.MutableLiveContainer
import com.example.biatechtestapp.di.IoDispatcher
import com.example.biatechtestapp.di.MainDispatcher
import com.example.biatechtestapp.model.tasks.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    @MainDispatcher private val dispatcherMain: CoroutineDispatcher,
    private val repository: TasksRepository
) : ViewModel() {

    private val _tasks = MutableLiveContainer<List<TaskItemUi>>()
    val tasks: LiveContainer<List<TaskItemUi>> = _tasks


    fun getTasks() = viewModelScope.launch(dispatcherIo) {
        repository.getTasks().collect {
            withContext(dispatcherMain) {
                _tasks.value = it.map { tasks ->
                    tasks.map {taskData->
                        if (taskData.isCurrent) {
                            TaskItemUi.Current(
                                taskData.id,
                                taskData.typeProduct,
                                taskData.addressFrom,
                                taskData.date,
                                taskData.addressTo,
                                taskData.details,
                                taskData.parameters
                            )
                        }else{
                            if (taskData.isDone) {
                                TaskItemUi.Done(
                                    taskData.id,
                                    taskData.typeProduct,
                                    taskData.addressFrom,
                                    taskData.date,
                                    taskData.addressTo
                                )
                            }
                            else {
                                TaskItemUi.Default(
                                    taskData.id,
                                    taskData.typeProduct,
                                    taskData.addressFrom,
                                    taskData.date,
                                    taskData.addressTo,
                                    taskData.details,
                                    taskData.parameters
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}