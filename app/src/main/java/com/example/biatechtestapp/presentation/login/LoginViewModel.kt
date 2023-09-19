package com.example.biatechtestapp.presentation.login

import androidx.lifecycle.ViewModel
import com.example.biatechtestapp.model.profile.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AccountRepository
): ViewModel() {

    fun setAuthorize(flag: Boolean){
        repository.setAuthorize(flag)
    }
}