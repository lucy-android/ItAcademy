package site.budanitskaya.todolist.secondscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface SecondScreenView : MvpView {
    fun loadView(title: String, description: String, deadline: String)
    fun onTaskSaved()
    fun showTimePickerDialog()
    fun showDatePickerDialog()
    fun setDateTime()
}