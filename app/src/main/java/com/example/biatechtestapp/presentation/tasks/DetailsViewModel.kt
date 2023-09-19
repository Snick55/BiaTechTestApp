package com.example.biatechtestapp.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.core.LiveContainer
import com.example.biatechtestapp.core.MutableLiveContainer
import com.example.biatechtestapp.di.IoDispatcher
import com.example.biatechtestapp.di.MainDispatcher
import com.example.biatechtestapp.model.tasks.TasksRepository
import com.example.biatechtestapp.presentation.tasks.entities.TaskDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    @MainDispatcher private val dispatcherMain: CoroutineDispatcher,
    private val repository: TasksRepository
) : ViewModel() {

    private val _taskDetail = MutableLiveContainer< TaskDetails>()
    val taskDetail: LiveContainer<TaskDetails> = _taskDetail

    fun getTaskById(id: Int) = viewModelScope.launch(dispatcherIo) {
        repository.getTaskById(id).collectLatest {
            withContext(dispatcherMain) {
                _taskDetail.value = it.map{
                    TaskDetails(
                        it.id,
                        it.typeProduct,
                        it.addressFrom,
                        it.date,
                        it.time,
                        it.addressTo,
                        it.details,
                        it.parameters,
                        it.typeCarcase,
                        it.number,
                        it.fio,
                        it.isCurrent,
                        it.city
                    )
                }
            }
        }
    }

    fun updateTask(id: Int) = viewModelScope.launch(dispatcherIo) {
        repository.updateTask(id)
    }

}