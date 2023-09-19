package com.example.biatechtestapp.model.profile

import android.content.SharedPreferences
import javax.inject.Inject

interface LoginPreference {

    fun isSignedIn(): Boolean

    fun login(isLogin: Boolean)


    class LoginPreferenceImpl @Inject constructor(private val sharedPreferences: SharedPreferences): LoginPreference{


        override fun isSignedIn(): Boolean {
          return sharedPreferences.getBoolean(KEY_SIGNIN,false)
        }

        override fun login(isLogin: Boolean) {
            sharedPreferences.edit().putBoolean(KEY_SIGNIN, isLogin).apply()
        }

        private companion object {
            const val KEY_SIGNIN = "KEY_SIGNIN"
        }
    }

}