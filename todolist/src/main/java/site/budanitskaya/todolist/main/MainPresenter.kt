package site.budanitskaya.todolist.main

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import site.budanitskaya.todolist.prefs.Preferences

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun setPreferenceMode(context: Context, number: Int){
        Preferences(context).darkMode = number
    }
}