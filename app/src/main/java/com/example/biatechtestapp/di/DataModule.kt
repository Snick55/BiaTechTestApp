package com.example.biatechtestapp.di

import com.example.biatechtestapp.model.PreferenceStore
import com.example.biatechtestapp.model.chat.ChatRepository
import com.example.biatechtestapp.model.profile.AccountRepository
import com.example.biatechtestapp.model.tasks.TasksCacheDataSource
import com.example.biatechtestapp.model.tasks.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule{

    @Binds
    abstract fun bindTasksRepository(repository: TasksRepository.TasksRepositoryImpl): TasksRepository

    @Binds
    abstract fun bindTasksCacheDataSource(cacheDataSource: TasksCacheDataSource.TasksCacheDataSourceImpl): TasksCacheDataSource

    @Binds
    abstract fun bindPreferenceStore(preferenceStore: PreferenceStore.PreferenceStoreImpl): PreferenceStore

    @Binds
    abstract fun bindChatRepository(repositoryImpl: ChatRepository.ChatRepositoryImpl): ChatRepository

    @Binds
    abstract fun bindAccountRepository(repositoryImpl: AccountRepository.AccountRepositoryImpl): AccountRepository

}