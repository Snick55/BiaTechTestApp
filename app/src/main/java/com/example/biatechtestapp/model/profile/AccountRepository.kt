package com.example.biatechtestapp.model.profile

import com.example.biatechtestapp.core.Container
import com.example.biatechtestapp.model.profile.entities.ProfileData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface AccountRepository {


    fun getProfile(): Flow<Container<ProfileData>>

    suspend fun isSignedIn(): Boolean

    fun setAuthorize(boolean: Boolean)

    class AccountRepositoryImpl @Inject constructor(
        private val loginPreference: LoginPreference
    ): AccountRepository{

        private val currentAccount = MutableStateFlow<ProfileData>(ProfileData(0,"Петров Иван Алексеевич","Водитель",1111,"РФ","Грузовая","А 000 АА 199", number = "+7 987 654 3210"))

        override fun getProfile(): Flow<Container<ProfileData>> = channelFlow{
            currentAccount
                .collectLatest {
                send(Container.Success(it))
            }
        }

        override suspend fun isSignedIn(): Boolean {
            delay(2000)
            return loginPreference.isSignedIn()
        }

        override fun setAuthorize(boolean: Boolean) {
            loginPreference.login(boolean)
        }
    }

}