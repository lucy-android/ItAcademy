package site.budanitskaya.backgroundwork

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.showUserLocation.setOnClickListener {
            checkLocationPermission()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.showBatteryCharge.setOnClickListener {
            if (binding.showBatteryCharge.isChecked) {
                viewModel.getAvailableMemoryAndBatteryCharge()
            } else if (!binding.showBatteryCharge.isChecked) {
                viewModel.cancelBatteryAndAvailableMemoryNotifications()
            }
        }
        if (isPermissionGranted()
        ) {
            binding.showUserLocation.visibility = View.GONE
            val serviceIntent = Intent(this, LocationService::class.java)
            startService(serviceIntent)
        }
    }

    private fun checkLocationPermission(): Boolean {
        return if (!isPermissionGranted()
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                requestPermission()
            } else {
                requestPermission()
            }
            false
        } else {
            true
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_FLAG
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_FLAG -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    if (isPermissionGranted()
                    ) {
                        binding.showUserLocation.visibility = View.GONE
                        val serviceIntent = Intent(this, LocationService::class.java)
                        startService(serviceIntent)
                    }
                }
                return
            }
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onPause(){
        super.onPause()
        val serviceIntent = Intent(this, LocationService::class.java)
        stopService(serviceIntent)
        viewModel.cancelBatteryAndAvailableMemoryNotifications()
    }
}