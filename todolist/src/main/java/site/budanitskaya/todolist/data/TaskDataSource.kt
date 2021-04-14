package site.budanitskaya.todolist.data

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import site.budanitskaya.todolist.MainApplication.Companion.applicationContext
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.di.DataBaseModule

@InstallIn(SingletonComponent::class)
@Module
object TaskDataSource: ITaskDataSource {

    private val taskDatabaseDao = DataBaseModule.provideLogDao(DataBaseModule.provideDatabase(applicationContext()))
    private var _taskList: MutableList<Task>

    init {
        runBlocking {
            _taskList = taskDatabaseDao!!.getTaskList().toMutableList()
            delay(1000L)

        }
    }

    val taskList: List<Task>
        get() = _taskList

    override fun deleteTask(task: Task) {
        when {
            _taskList.isNotEmpty() -> {
                CoroutineScope(Dispatchers.Main).launch {
                    taskDatabaseDao?.delete(task)
                    _taskList.remove(task)
                }

            }
            else -> _taskList
        }
    }

    override fun updateTask(oldTask: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            taskDatabaseDao!!.update(oldTask)
        }
    }

    override fun insertTask(task: Task) {
        CoroutineScope(Dispatchers.Main).launch {
            _taskList.add(task)
            taskDatabaseDao!!.insert(task)
        }
    }
}