package site.budanitskaya.todolist.firstscreen

import moxy.MvpPresenter
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.util.TaskList

class FirstScreenPresenter : MvpPresenter<FirstScreenView>() {

    private lateinit var taskList: List<Task>

    init {
        taskList = TaskList.taskList
    }

    fun deleteTask(position: Int) {
        val task: Task = TaskList.taskList[position]
        TaskList.deleteTask(task)
        viewState.updateView(position)
    }
}