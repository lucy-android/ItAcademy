package site.budanitskaya.todolist.util

import site.budanitskaya.todolist.Injection
import site.budanitskaya.todolist.MainApplication
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase

object TaskList {

    private val taskDatabaseDao = Injection.provideTaskDataSource()
    private val _taskList: MutableList<Task> = taskDatabaseDao?.getTaskList()?.toMutableList()!!

    val taskList: List<Task>
        get() = _taskList

    fun deleteTask(task: Task) {
        when {
            _taskList.isNotEmpty() -> {
                taskDatabaseDao?.delete(task)
                _taskList.remove(task)
            }
            else -> _taskList
        }
    }

    fun updateTask(oldTask: Task) {
        taskDatabaseDao!!.update(oldTask)
    }

    fun insertTask(task: Task) {
        _taskList.add(task)
        taskDatabaseDao!!.insert(task)
    }

}