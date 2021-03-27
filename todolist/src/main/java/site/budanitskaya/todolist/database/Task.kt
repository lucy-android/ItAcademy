package site.budanitskaya.todolist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,
    @ColumnInfo(name = "task_title")
    var taskTitle: String = "default_task_title",
    @ColumnInfo(name = "task_description")
    var taskDescription: String = "default_task_description"
)

