package site.budanitskaya.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task

class ToDoListAdapter(
    private val tasks: List<Task>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val taskItemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item_layout, parent, false)

        return TodoItemViewHolder(taskItemLayout)
    }

    class TodoItemViewHolder(taskItemLayout: View) : RecyclerView.ViewHolder(taskItemLayout) {

        private val root: View = taskItemLayout.rootView

        fun bind() {
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TodoItemViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = tasks.size
}