package site.budanitskaya.todolist.firstscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FirstScreenView: MvpView {
    fun onUpdateView(position: Int)
    fun showAcionMode(position: Int)
    fun showTaskDeleteDialog(position: Int)
}