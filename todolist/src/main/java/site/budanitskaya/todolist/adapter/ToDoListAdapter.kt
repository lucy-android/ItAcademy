package site.budanitskaya.todolist.adapter

import android.animation.LayoutTransition
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task

class ToDoListAdapter(
    private val tasks: List<Task>,
    private val onLongClick: (Int) -> Boolean

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val taskItemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item_layout, parent, false)
        return TodoItemViewHolder(taskItemLayout, tasks, onLongClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodoItemViewHolder -> {
                holder.bind(position)
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    class TodoItemViewHolder(
        view: View,
        val tasks: List<Task>,
        private val onLongClick: (Int) -> Boolean
    ) : RecyclerView.ViewHolder(view) {

        private val root: View = view.rootView
        private val taskName: TextView = root.findViewById<TextView>(R.id.task_name)
        private val taskDecription = root.findViewById<TextView>(R.id.task_description)
        private val dateAndTime = root.findViewById<TextView>(R.id.date_and_time)
        private val priority = root.findViewById<TextView>(R.id.task_priority)
        fun bind(position: Int) {


            taskName.text = tasks[position].taskTitle
            taskDecription.text = tasks[position].taskDescription
            dateAndTime.text = tasks[position].dateAndTime
            priority.text = "Task priority: ${tasks[position].priority.toString()}"
            root.setOnLongClickListener {
                onLongClick(position)
            }
        }
    }
}