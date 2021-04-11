package site.budanitskaya.todolist

import android.content.Context
import site.budanitskaya.todolist.MainApplication.Companion.applicationContext
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase
import site.budanitskaya.todolist.database.TaskDatabaseDao

object Injection {

    fun provideTaskDataSource(): TaskDatabaseDao? {
        val database = TaskDatabase.getInstance(applicationContext())
        return database.taskDao()
    }

}