package site.budanitskaya.todolist.firstscreen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.util.TaskDataSource


@InjectViewState
class FirstScreenPresenter : MvpPresenter<FirstScreenView>() {
    var tasks: MutableList<Task> = TaskDataSource.taskList.toMutableList()
}