package site.budanitskaya.todolist


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import site.budanitskaya.todolist.database.Task
import site.budanitskaya.todolist.database.TaskDatabase
import site.budanitskaya.todolist.secondscreen.SecondFragmentDirections
import site.budanitskaya.todolist.util.TaskList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
/*        val navController = this.findNavController(R.id.nav_host_fragment)*/

        this.findNavController(R.id.nav_host_fragment).navigate(SecondFragmentDirections.actionSecondFragmentToFirstFragment())
        return true
    }

}


