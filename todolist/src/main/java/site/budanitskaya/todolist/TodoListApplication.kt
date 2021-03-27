package site.budanitskaya.todolist

import android.app.Application
import androidx.room.Room
import site.budanitskaya.todolist.database.TaskDatabase


class TodoListApplication : Application() {

    companion object {
        lateinit var instance: TodoListApplication

        @JvmName("getInstance1")
        fun getInstance(): TodoListApplication {
            return instance
        }
    }

    lateinit var taskDatabase: TaskDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        taskDatabase = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, "todo_list_table"
        )
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): TaskDatabase? {
        return taskDatabase
    }

}