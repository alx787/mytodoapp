package ru.alx.todoapp.fragments.list

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.alx.todoapp.R
import ru.alx.todoapp.data.models.Priority
import ru.alx.todoapp.data.models.ToDoData

class ListHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var toDoData: ToDoData

    private val title_txt: TextView = itemView.findViewById(R.id.title_txt)
    private val description_txt: TextView = itemView.findViewById(R.id.description_txt)
    private val priority_indicator: CardView = itemView.findViewById(R.id.priority_indicator)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        v.findNavController().navigate(R.id.action_listFragment_to_updateFragment)
//         Toast.makeText(v.context, " clicked!", Toast.LENGTH_SHORT).show()
    }

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