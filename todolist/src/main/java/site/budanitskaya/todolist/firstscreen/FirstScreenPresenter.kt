package site.budanitskaya.todolist.firstscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.data.TaskDataSource


@InjectViewState
class FirstScreenPresenter : MvpPresenter<FirstScreenView>() {
    var tasks: MutableList<Task> = TaskDataSource.taskList.toMutableList()

    fun deleteTask(position: Int) {
        val task: Task = tasks[position]
        tasks.removeAt(position)
        TaskDataSource.deleteTask(task)
        viewState.onItemRemoved(position)
    }
}