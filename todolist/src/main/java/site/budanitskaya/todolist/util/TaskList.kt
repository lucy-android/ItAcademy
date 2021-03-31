package site.budanitskaya.todolist.util

import site.budanitskaya.todolist.MainApplication
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase
import java.util.*

class TaskList {

    companion object {
        private val taskDataBase = TaskDatabase.getInstance(MainApplication.applicationContext())

        private val taskDatabaseDao = taskDataBase.taskDao()

        private val _taskList: MutableList<Task> = mutableListOf()

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


        fun updateTask(newTask: Task, position: Int) {

            taskDatabaseDao?.update(newTask)

            for (i in _taskList.indices) {
                if (i == position) {
                    _taskList[position] = newTask
                }
            }
        }


        fun insertTask(task: Task, position: Int) {
            _taskList.add(position, task)
            taskDatabaseDao!!.insert(task)
        }
    }
}