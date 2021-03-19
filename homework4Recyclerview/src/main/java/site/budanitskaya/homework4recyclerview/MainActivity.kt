package site.budanitskaya.homework4recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import site.budanitskaya.homework4recyclerview.DataUtils.Companion.score
import site.budanitskaya.homework4recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val bottomNav = binding.bottomNav

        val navController: NavController = Navigation.findNavController(this, R.id.fragment)

        bottomNav.setupWithNavController(navController)
    }
}