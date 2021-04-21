package site.budanitskaya.todolist.secondscreen

import android.content.Context
import android.text.format.DateUtils
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.data.TaskDataSource
import java.lang.Exception
import java.util.*

@InjectViewState
class SecondScreenPresenter : MvpPresenter<SecondScreenView>() {

    private lateinit var task: Task
    private var dateAndTime: Calendar = Calendar.getInstance()
    private var isNew = true

    fun loadTask(isNew: Boolean, position: Int) {
        if (!isNew) {
            task = TaskDataSource.taskList[position]
            viewState.loadView(task.taskTitle, task.taskDescription, task.dateAndTime, task.priority)
            this.isNew = false
        } else {
            task = Task()
        }
    }

    fun updateTask(title: String, description: String, deadline: String, priority: Int) {
        TaskDataSource.updateTask(task)
        task.taskTitle = title
        task.taskDescription = description
        task.dateAndTime = deadline
        task.priority = priority
        viewState.navigateToFirstFragment()
    }

    fun insertTask(title: String, description: String, deadline: String, priority: Int) {
        TaskDataSource.insertTask(task)
        task.taskTitle = title
        task.taskDescription = description
        task.dateAndTime = deadline
        task.priority = priority
        viewState.navigateToFirstFragment()
    }

    fun setDeadlineHour() = when (isNew) {
        true -> dateAndTime[Calendar.HOUR]
        else -> task.dateAndTime.substringAfterLast(", ").substring(0, 2).toInt()
    }

    fun setDeadlineMinute() = when (isNew) {
        true -> dateAndTime[Calendar.MINUTE]
        else -> task.dateAndTime.takeLast(2).toInt()
    }

    fun setDeadlineMonth() = when (isNew) {
        true -> dateAndTime[Calendar.MONTH]
        else -> when (task.dateAndTime.substringBefore(' ')) {
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

    fun setDeadlineYear() = when (isNew) {
        true -> dateAndTime[Calendar.YEAR]
        else -> task.dateAndTime.substringAfter(", ").substring(0, 4).toInt()
    }

    fun setDeadlineDay() = when (isNew) {
        true -> dateAndTime[Calendar.DAY_OF_MONTH]
        else -> task.dateAndTime.substringAfter(" ").substringBefore(", ").toInt()
    }

    fun formatTimeDate(context: Context): String {
        return DateUtils.formatDateTime(
            context,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME
        )
    }

    fun setTime(hourOfDay: Int, minute: Int) {
        dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
        dateAndTime[Calendar.MINUTE] = minute
        viewState.updateDateTime()
    }

    fun setDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateAndTime[Calendar.YEAR] = year
        dateAndTime[Calendar.MONTH] = monthOfYear
        dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
        viewState.updateDateTime()
    }
}