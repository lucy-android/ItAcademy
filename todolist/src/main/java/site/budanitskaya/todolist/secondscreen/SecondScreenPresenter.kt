package site.budanitskaya.todolist.secondscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.util.TaskDataSource
import java.lang.Exception
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

    fun updateTask(title: String, description: String, deadline: String) {
        TaskDataSource.updateTask(task)
        task.taskTitle = title
        task.taskDescription = description
        task.dateAndTime = deadline
        viewState.onTaskUpdated()
    }

    fun insertTask(title: String, description: String, deadline: String) {
        TaskDataSource.insertTask(task)
        task.taskTitle = title
        task.taskDescription = description
        task.dateAndTime = deadline
        viewState.onTaskInserted()
    }

    fun setDeadlineHour(): Int = task.dateAndTime.substringAfterLast(
        ", "
    ).substring(0, 2).toInt()


    fun setDeadlineMinute() = task.dateAndTime.takeLast(2).toInt()

    fun setDeadlineMonth(): Int {
        return when (task.dateAndTime.substringBefore(' ')) {
            "January" -> 0
            "February" -> 1
            "March" -> 2
            "April" -> 3
            "May" -> 4
            "June" -> 5
            "July" -> 6
            "August" -> 7
            "September" -> 8
            "October" -> 9
            "November" -> 10
            "December" -> 11
            else -> throw Exception()
        }
    }

    fun setDeadlineYear(): Int {
        return task.dateAndTime.substringAfter(' ').substring(4, 8).toInt()
    }

    fun setDeadlineDay() = task.dateAndTime.substringAfter(' ').substring(0, 2).toInt()

}