package ru.alx.todoapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.alx.todoapp.R
import ru.alx.todoapp.data.models.Priority
import ru.alx.todoapp.data.models.ToDoData

class ListAdapter: RecyclerView.Adapter<ListHolder>() {

    var dataList = emptyList<ToDoData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val toDoData = dataList[position]
        holder.bind(toDoData)


    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun setData(toDoDataList: List<ToDoData>) {
        this.dataList = toDoDataList
        notifyDataSetChanged()
    }
}