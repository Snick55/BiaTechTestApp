package com.example.biatechtestapp.model.tasks

import android.util.Log
import com.example.biatechtestapp.model.tasks.entities.TaskDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TasksCacheDataSource {

    suspend fun getTasks(): Flow<List<TaskDb>>
    suspend fun inflateTable()

    suspend fun getTaskById(id:Int): Flow<TaskDb>
   suspend fun updateTask(taskDb: TaskDb)

    class TasksCacheDataSourceImpl @Inject constructor(
        private val dao: TasksDao
    ) : TasksCacheDataSource {


        private val initialTasks = listOf<TaskDb>(
            TaskDb(
                1,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = true,
                isDone = false
            ),    TaskDb(
                2,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = false,
                isDone = false
            ),    TaskDb(
                3,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = false,
                isDone = true
            ),    TaskDb(
                4,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = false,
                isDone = false
            ),    TaskDb(
                5,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = false,
                isDone = false
            ),    TaskDb(
                6,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = false,
                isDone = true
            ),    TaskDb(
                7,
                "Мебель",
                "Склад 1, Ул. Комсомольская 384",
                "18.04.2023",
                "12:00",
                "Склад 11, ул. Радищева 145В",
                "Прописанные детали заказа",
                "Прописанные параметры по оплате",
                "Тентованный",
                "+7 800 896 52 63",
                "Иванов Владимир Иосифович",
                isCurrent = false,
                isDone = false
            )
        )

        override suspend fun getTasks(): Flow<List<TaskDb>> {
            delay(2000) // fake delay
            return dao.getTasks()
        }

        override suspend fun inflateTable() {
            initialTasks.forEach {
                dao.insertTask(it)
            }
        }

        override suspend fun updateTask(taskDb: TaskDb) {
            dao.updateTask(taskDb)
        }

        override suspend fun getTaskById(id: Int): Flow<TaskDb> {
           return dao.getTaskById(id)
        }
    }
}