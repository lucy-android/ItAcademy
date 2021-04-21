package site.budanitskaya.todolist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import dagger.hilt.android.AndroidEntryPoint
import site.budanitskaya.todolist.prefs.Preferences
import site.budanitskaya.todolist.secondscreen.SecondFragmentDirections

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkTheme()
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        this.findNavController(R.id.nav_host_fragment).navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
        return true
    }

    private fun checkTheme() {
        when (Preferences(this).darkMode) {
            0 -> {
                setLightMode()
            }
            1 -> {
                setDarkMode()
            }
            2 -> {
                setDefaultMode()
            }
        }
    }

    override fun showThemeSelectDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_theme_text))
        val styles = arrayOf("Light", "Dark", "System default")
        val checkedItem = Preferences(this).darkMode

        builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->
            when (which) {
                0 -> {
                    setLightMode()
                    presenter.setPreferenceMode(this, 0)
                }
                1 -> {
                    setDarkMode()
                    presenter.setPreferenceMode(this, 1)
                }
                2 -> {
                    setDefaultMode()
                    presenter.setPreferenceMode(this, 2)
                }
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.settings) {
            showThemeSelectDialog()
            true
        } else super.onOptionsItemSelected(item)

    override fun setLightMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    override fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    override fun setDefaultMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        delegate.applyDayNight()
    }
}