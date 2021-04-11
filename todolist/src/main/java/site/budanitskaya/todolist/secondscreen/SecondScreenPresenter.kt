package site.budanitskaya.todolist.secondscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.util.TaskDataSource

@InjectViewState
class SecondScreenPresenter : MvpPresenter<SecondScreenView>() {

    lateinit var task: Task

    fun insertTask() {
        TaskDataSource.insertTask(task)
        viewState.onTaskInserted()
    }

    fun updateTask() {
        TaskDataSource.updateTask(task)
        viewState.onTaskUpdated()
    }

    fun prepareTaskOpen(position: Int) {
        task = TaskDataSource.taskList[position]
        viewState.onTaskOpened(task)

    }

}