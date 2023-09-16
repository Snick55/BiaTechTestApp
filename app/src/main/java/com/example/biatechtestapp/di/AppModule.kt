package com.example.biatechtestapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.biatechtestapp.model.tasks.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        TasksDatabase::class.java,
        "tasks_list"
    ).build()


    @Singleton
    @Provides
    fun provideTasksDao(db: TasksDatabase) = db.tasksDao()


    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
    }

    private companion object {
        const val APP_PREF = "APP_PREF"
    }

}