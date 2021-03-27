package site.budanitskaya.todolist.database

import androidx.room.*

@Dao
interface TaskDatabaseDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT COUNT(task_title) FROM todo_list_table")
    fun getRowCount(): Int

    @Query("SELECT * FROM todo_list_table")
    fun getTaskList(): List<Task>
}