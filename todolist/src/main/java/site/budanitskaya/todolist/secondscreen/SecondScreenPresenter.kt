package site.budanitskaya.todolist.secondscreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.format.DateUtils
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.datasource.TaskDataSource
import java.util.*

@InjectViewState
class SecondScreenPresenter : MvpPresenter<SecondScreenView>() {

    private lateinit var task: Task
    private var dateAndTime: Calendar = Calendar.getInstance()

    fun insertTask(taskTitle: String, taskDescription: String, taskDeadline: String) {
        TaskDataSource.insertTask(task)
        task.taskTitle = taskTitle
        task.taskDescription = taskDescription
        task.dateAndTime = taskDeadline
        viewState.onTaskSaved()
    }

    fun updateTask(taskTitle: String, taskDescription: String, taskDeadline: String) {
        TaskDataSource.updateTask(task)
        task.taskTitle = taskTitle
        task.taskDescription = taskDescription
        task.dateAndTime = taskDeadline
        viewState.onTaskSaved()
    }

    fun prepareTaskOpen(position: Int) {
        task = TaskDataSource.taskList[position]
        viewState.onTaskOpened(task)

    }

    fun loadTask(task: Task) {
        this.task = task
    }


    fun setDate(context: Context) {
        DatePickerDialog(
            context, date,
            dateAndTime[Calendar.YEAR],
            dateAndTime[Calendar.MONTH],
            dateAndTime[Calendar.DAY_OF_MONTH]
        )
            .show()
    }

    fun setTime(context: Context) {
        TimePickerDialog(
            context, time,
            dateAndTime[Calendar.HOUR_OF_DAY],
            dateAndTime[Calendar.MINUTE], true
        )
            .show()
    }

    private var time = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
        dateAndTime[Calendar.MINUTE] = minute
        viewState.setInitialDateTime()
    }

    private var date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        dateAndTime[Calendar.YEAR] = year
        dateAndTime[Calendar.MONTH] = monthOfYear
        dateAndTime[Calendar.DAY_OF_MONTH] = dayOfMonth
        viewState.setInitialDateTime()
    }

    fun formatDateTime(context: Context): String {
        return DateUtils.formatDateTime(
            context,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME
        )
    }
}