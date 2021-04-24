package site.budanitskaya.backgroundwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import site.budanitskaya.backgroundwork.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the ViewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.performWork()

        val myServiceIntent = Intent(this,  LocationService::class.java)
/*        myServiceIntent.putExtra(inputExtra, "546")*/
        ContextCompat.startForegroundService(this, myServiceIntent)
    }

/*    fun startService() {


    }

    fun stopService() {
        val serviceIntent = Intent(this, LocationService::class.java)
        stopService(serviceIntent)
    }*/
}