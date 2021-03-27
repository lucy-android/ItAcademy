package site.budanitskaya.todolist


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskDataBase = TaskDatabase.getInstance(applicationContext)

        val taskDatabaseDao = taskDataBase.taskDao()

        val task = Task()
        task.taskTitle = "Get up!"
        task.taskDescription = "Wake up early in the morning and get up from bed"

        if(taskDatabaseDao != null) {
            taskDatabaseDao.insert(task)
            Log.d("3141592", "onCreate: ${task.taskTitle}, ${task.taskDescription}")
        }
        if (taskDatabaseDao != null) {
            Log.d("3141592", "onCreate: ${taskDatabaseDao.getRowCount()}")
        }


    }
}