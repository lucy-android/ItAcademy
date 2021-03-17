package site.budanitskaya.homework4recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment)

        //Setting the navigation controller to Bottom Nav
        bottomNav.setupWithNavController(navController)


        //Setting up the action bar
/*        NavigationUI.setupActionBarWithNavController(this, navController)*/

    }

    //Setting Up the back button
/*    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }*/
}