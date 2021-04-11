package site.budanitskaya.todolist.database

import site.budanitskaya.todolist.MainApplication

class TaskList {

    companion object {
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
    }
}