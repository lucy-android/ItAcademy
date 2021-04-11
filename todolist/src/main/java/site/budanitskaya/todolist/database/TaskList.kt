package site.budanitskaya.todolist.database

import io.reactivex.Flowable
import site.budanitskaya.todolist.Injection
import site.budanitskaya.todolist.MainApplication

object TaskList {

    fun getTaskList(): Flowable<List<Task>> {
        return Injection.provideTaskDataSource()!!.getTaskList()
    }

    /*companion object {
        private val taskDataBase = TaskDatabase.getInstance(MainApplication.applicationContext())
        private val taskDatabaseDao = taskDataBase.taskDao()
        private val _taskList: MutableList<Task> = taskDatabaseDao?.getTaskList()?.toMutableList()!!

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

        fun updateTask(oldTask: Task) {
            taskDatabaseDao!!.update(oldTask)
        }

        fun insertTask(task: Task) {
            _taskList.add(task)
            taskDatabaseDao!!.insert(task)
        }
    }*/
}