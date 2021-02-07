package ru.alx.todoapp.data.repository

import androidx.lifecycle.LiveData
import ru.alx.todoapp.data.ToDoDao
import ru.alx.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }
}