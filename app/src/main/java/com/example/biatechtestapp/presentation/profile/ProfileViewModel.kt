package com.example.biatechtestapp.presentation.profile

import android.accounts.Account
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biatechtestapp.core.LiveContainer
import com.example.biatechtestapp.core.MutableLiveContainer
import com.example.biatechtestapp.di.IoDispatcher
import com.example.biatechtestapp.di.MainDispatcher
import com.example.biatechtestapp.model.chat.ChatRepository
import com.example.biatechtestapp.model.profile.AccountRepository
import com.example.biatechtestapp.presentation.profile.entities.ProfileUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @IoDispatcher private val dispatcherIo: CoroutineDispatcher,
    @MainDispatcher private val dispatcherMain: CoroutineDispatcher,
    private val repository: AccountRepository
): ViewModel() {

    private val _profile = MutableLiveContainer<ProfileUi>()
    val profile: LiveContainer<ProfileUi> = _profile


    fun getProfile() = viewModelScope.launch(dispatcherIo) {
        delay(1000)
        repository.getProfile().collect{
            withContext(dispatcherMain){
                _profile.value = it.map{
                    ProfileUi(it.id,it.fio,it.role,it.numOfTabel,it.nationality,it.typeOfCar,it.numbersOfCar,it.avatarUrl,it.number)
                }
            }
        }
    }

    fun logout() {
        repository.setAuthorize(false)
    }

}