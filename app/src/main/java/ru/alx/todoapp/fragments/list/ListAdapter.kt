package ru.alx.todoapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.alx.todoapp.R
import ru.alx.todoapp.data.models.Priority
import ru.alx.todoapp.data.models.ToDoData

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private lateinit var toDoData: ToDoData

        private val title_txt: TextView = itemView.findViewById(R.id.title_txt)
        private val description_txt: TextView = itemView.findViewById(R.id.description_txt)
        private val priority_indicator: CardView = itemView.findViewById(R.id.priority_indicator)

        fun bind(toDoData: ToDoData) {
            this.toDoData = toDoData
            title_txt.text = toDoData.title
            description_txt.text= toDoData.description

            when (toDoData.priority) {
                Priority.HIGH -> priority_indicator.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.red))
                Priority.MEDIUM -> priority_indicator.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.yellow))
                Priority.LOW -> priority_indicator.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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