package ru.alx.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.alx.todoapp.data.models.ToDoData

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(todoData: ToDoData)

    @Update
    suspend fun updateData(todoData: ToDoData)

    @Delete
    suspend fun deleteData(todoData: ToDoData)

}