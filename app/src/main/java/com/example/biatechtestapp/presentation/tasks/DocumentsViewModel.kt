package com.example.biatechtestapp.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.di.IoDispatcher
import com.example.biatechtestapp.di.MainDispatcher
import com.example.biatechtestapp.model.tasks.TasksRepository
import com.example.biatechtestapp.model.tasks.entities.TaskData
import com.example.biatechtestapp.presentation.tasks.entities.TaskDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    private val repository: TasksRepository
) : ViewModel() {


    fun updateTask(task: TaskDetails) = viewModelScope.launch(dispatcherIo) {
        repository.updateTask(
            TaskData(
                task.id,
                task.typeProduct,
                task.addressFrom,
                task.date,
                task.time,
                task.addressTo,
                task.details,
                task.parameters,
                task.typeCarcase,
                task.number,
                task.fio,
                isCurrent = false,
                isDone = true
            )
        )
    }
}