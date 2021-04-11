package site.budanitskaya.todolist.util

import kotlinx.coroutines.*
import site.budanitskaya.todolist.Injection
import site.budanitskaya.todolist.database.Task

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
                CoroutineScope(Dispatchers.Main).launch {
                    taskDatabaseDao?.delete(task)
                    _taskList.remove(task)
                }

            }
            else -> _taskList
        }
    }

    fun updateTask(oldTask: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            taskDatabaseDao!!.update(oldTask)
        }
    }

    fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            _taskList.add(task)
            taskDatabaseDao!!.insert(task)
        }
    }

}