package site.budanitskaya.todolist.secondscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.util.TaskDataSource
import java.util.*

@InjectViewState
class SecondScreenPresenter : MvpPresenter<SecondScreenView>() {

    private lateinit var task: Task
    private var dateAndTime: Calendar = Calendar.getInstance()

    fun loadTask(isNew: Boolean, position: Int) {
        if (!isNew) {
            task = TaskDataSource.taskList[position]
            viewState.loadView(task.taskTitle, task.taskDescription, task.dateAndTime)
        } else {
            task = Task()
        }
    }

    fun updateTask(title: String, description: String, deadline: String){
        TaskDataSource.updateTask(task)
        task.taskTitle = title
        task.taskDescription = description
        task.dateAndTime = deadline
        viewState.onTaskUpdated()
    }

    fun insertTask(title: String, description: String, deadline: String){
        TaskDataSource.insertTask(task)
        task.taskTitle = title
        task.taskDescription = description
        task.dateAndTime = deadline
        viewState.onTaskInserted()
    }
    fun setTime(){

    }

    fun setDate(){

    }
}