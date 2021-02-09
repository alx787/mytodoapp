package ru.alx.todoapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import ru.alx.todoapp.data.models.Priority

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData(
        @PrimaryKey(autoGenerate = true)
    var id: Int,
        var title: String,
        var priority: Priority,
        var description: String
): Parcelable