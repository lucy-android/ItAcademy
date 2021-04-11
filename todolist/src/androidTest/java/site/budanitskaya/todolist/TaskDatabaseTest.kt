package site.budanitskaya.todolist

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase
import site.budanitskaya.todolist.database.TaskDatabaseDao
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {

    private lateinit var taskDao: TaskDatabaseDao
    private lateinit var db: TaskDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        taskDao = db.taskDao()!!
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTask() {
        val task = Task()
/*        taskDao.insert(task)
        taskDao.update(task)*/
    }
}