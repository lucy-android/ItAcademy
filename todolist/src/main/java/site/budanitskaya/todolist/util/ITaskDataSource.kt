package site.budanitskaya.todolist.util

import site.budanitskaya.todolist.database.Task

interface ITaskDataSource {
    fun deleteTask(task: Task)
    fun updateTask(task: Task)
    fun insertTask(task: Task)
}