package com.example.biatechtestapp.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.core.LiveContainer
import com.example.biatechtestapp.core.MutableLiveContainer
import com.example.biatechtestapp.model.tasks.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main,
    private val repository: TasksRepository
) : ViewModel() {

    private val _tasks = MutableLiveContainer<List<TaskItemUi>>()
    val tasks: LiveContainer<List<TaskItemUi>> = _tasks


    fun getTasks() = viewModelScope.launch(dispatcherIo) {
        repository.getTasks().collect {
            withContext(dispatcherMain) {
                _tasks.value = it.map { tasks ->
                    tasks.map {
                        if (it.isCurrent) {
                            TaskItemUi.Current(
                                it.id,
                                it.typeProduct,
                                it.addressFrom,
                                it.date,
                                it.addressTo,
                                it.details,
                                it.parameters
                            )
                        } else if (it.isDone) {
                            TaskItemUi.Done(
                                it.id,
                                it.typeProduct,
                                it.addressFrom,
                                it.date,
                                it.addressTo
                            )
                        } else {
                            TaskItemUi.Default(
                                it.id,
                                it.typeProduct,
                                it.addressFrom,
                                it.date,
                                it.addressTo,
                                it.details,
                                it.parameters
                            )
                        }
                    }
                }
            }
        }
    }


}