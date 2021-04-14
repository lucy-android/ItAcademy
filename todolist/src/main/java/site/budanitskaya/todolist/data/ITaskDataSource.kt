package site.budanitskaya.todolist.data

import site.budanitskaya.todolist.database.Task

interface ITaskDataSource {
    fun deleteTask(task: Task)
    fun updateTask(task: Task)
    fun insertTask(task: Task)
}