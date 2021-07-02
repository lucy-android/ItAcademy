package site.budanitskaya.todolist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.todolist.R
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.impl.OnSwipeTouchListener


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
        private val motionLayout = root.findViewById<MotionLayout>(R.id.motion_layout)

        @SuppressLint("ClickableViewAccessibility")
        fun bind(position: Int) {
            taskName.text = tasks[position].taskTitle
            taskDecription.text = tasks[position].taskDescription
            dateAndTime.text = tasks[position].dateAndTime
            priority.text = "Task priority: ${tasks[position].priority.toString()}"

            root.setOnTouchListener(
                Listener(
                    root as MotionLayout,
                    root.context,
                    onLongClick,
                    position
                )
            )

        } /*{ view, motion ->

                if(motion.action == MotionEvent.ACTION_DOWN){
                    with(root as MotionLayout) {

                        if (currentState == R.id.click_start) {
                            onLongClick(position)
                            setTransition(R.id.click_start, R.id.click_end)
                            transitionToEnd()
                        } else if (currentState == R.id.click_end) {
                            setTransition(R.id.click_end, R.id.click_start)
                            transitionToEnd()
                        }
                    }
                } else if (motion.action == MotionEvent.ACTION_MOVE){

                }

                false
            }*/

    }

    class Listener(
        val root: MotionLayout,
        val context: Context,
        private val onLongClick: (Int) -> Boolean,
        val position: Int
    ) : OnSwipeTouchListener(context) {
        override fun onSwipeRight() {
            super.onSwipeRight()
            Toast.makeText(context, "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
            if (root.currentState == R.id.swipe_start) {
                root.setTransition(R.id.swipe_start, R.id.swipe_end)
            } else {
                root.setTransition(R.id.swipe_end, R.id.swipe_start)
            }
            root.transitionToEnd()
        }

        override fun onLongClick() {
            super.onLongClick()
            onLongClick(position)
        }


    }
}






