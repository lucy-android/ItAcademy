package site.budanitskaya.todolist.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun showThemeSelectDialog()
    fun setDarkMode()
    fun setLightMode()
    fun setDefaultMode()
}