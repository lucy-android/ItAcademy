package site.budanitskaya.todolist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase

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
            is TodoItemViewHolder -> holder.bind(position)
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

        fun bind(position: Int) {
            taskName.text = tasks[position].taskTitle
            taskDecription.text = tasks[position].taskDescription

            root.setOnLongClickListener {
                onLongClick(position)
            }

            /*val taskDataBase = TaskDatabase.getInstance(root.context)

            val taskDatabaseDao = taskDataBase.taskDao()!!

            val task: Task = taskDatabaseDao.getTaskList()[position]

            taskDatabaseDao.delete(task)*/

        }

    }
}