package com.example.biatechtestapp.model

import android.content.SharedPreferences
import javax.inject.Inject

interface PreferenceStore {

    fun isFirstRun():Boolean

    fun setFlag(flag: Boolean)


    class PreferenceStoreImpl @Inject constructor(private val sharedPreferences: SharedPreferences):PreferenceStore{

        override fun setFlag(flag: Boolean) {
            sharedPreferences.edit().putBoolean(KEY_RUN, flag).apply()
        }

        override fun isFirstRun(): Boolean {
            return sharedPreferences.getBoolean(KEY_RUN, true)
        }

        private companion object {
            const val KEY_RUN = "RUN"
        }

    }
}