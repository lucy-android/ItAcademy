package site.budanitskaya.todolist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase
import site.budanitskaya.todolist.database.TaskDatabaseDao
import site.budanitskaya.todolist.data.TaskDataSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java, "todo_list_table"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: TaskDatabase): TaskDatabaseDao? {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskDataSource(): List<Task> {
        return TaskDataSource.taskList
    }

}