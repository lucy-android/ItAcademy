package site.budanitskaya.todolist.secondscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import site.budanitskaya.todolist.database.Task


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SecondScreenView: MvpView {

    fun onTaskInserted()

    fun onTaskUpdated()

    fun onTaskOpened(task: Task)

}