package site.budanitskaya.todolist.util

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import site.budanitskaya.todolist.Injection
import site.budanitskaya.todolist.MainApplication
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase

object TaskList {

    private val taskDatabaseDao = Injection.provideTaskDataSource()
    private var _taskList: MutableList<Task>

    init {
        runBlocking {
            _taskList = taskDatabaseDao!!.getTaskList().toMutableList()
            delay(1000L)

        }
    }

    val taskList: List<Task>
        get() = _taskList

    fun deleteTask(task: Task) {
        when {
            _taskList.isNotEmpty() -> {
                GlobalScope.launch {
                    taskDatabaseDao?.delete(task)
                    _taskList.remove(task)
                }

            }
            else -> _taskList
        }
    }

    fun updateTask(oldTask: Task) {
        GlobalScope.launch {
            taskDatabaseDao!!.update(oldTask)
        }
    }

    fun insertTask(task: Task) {
        GlobalScope.launch {
            _taskList.add(task)
            taskDatabaseDao!!.insert(task)
        }
    }

}