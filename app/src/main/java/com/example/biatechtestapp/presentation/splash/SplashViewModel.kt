package com.example.biatechtestapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.di.IoDispatcher
import com.example.biatechtestapp.di.MainDispatcher
import com.example.biatechtestapp.model.profile.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    @MainDispatcher private val dispatcherMain: CoroutineDispatcher,
    private val repository: AccountRepository
) : ViewModel() {

    private val _isSignedIn = MutableLiveData<Boolean>()
    val isSignedIn: LiveData<Boolean> = _isSignedIn


    fun isSignedIn() = viewModelScope.launch(dispatcherIo) {
        withContext(dispatcherMain) {
            _isSignedIn.value = repository.isSignedIn()
        }
    }

}