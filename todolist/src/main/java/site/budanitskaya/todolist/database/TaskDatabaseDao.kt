package site.budanitskaya.todolist.database

import androidx.room.*

@Dao
interface TaskDatabaseDao {

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT COUNT(task_title) FROM todo_list_table")
    suspend fun getRowCount(): Int

    @Query("SELECT * FROM todo_list_table")
    suspend fun getTaskList(): List<Task>

    @Query("DELETE FROM todo_list_table")
    suspend fun clear()
}