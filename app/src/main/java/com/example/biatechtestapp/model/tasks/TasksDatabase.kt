package com.example.biatechtestapp.model.tasks

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.biatechtestapp.model.tasks.entities.TaskDb

@Database(entities = [TaskDb::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

}