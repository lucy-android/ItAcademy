package site.budanitskaya.backgroundwork

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import site.budanitskaya.backgroundwork.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
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
        getLocationInfo()
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

    @SuppressLint("MissingPermission")
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
                        getLocationInfo()
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

    @SuppressLint("MissingPermission")
    fun getLocationInfo() {
        locationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val providers = locationManager.getProviders(true)
        for (provider in providers) {
            locationManager.requestLocationUpdates(
                provider!!, 1000, 0f,
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {}
                    override fun onProviderDisabled(provider: String) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onStatusChanged(
                        provider: String, status: Int,
                        extras: Bundle
                    ) {
                    }
                })
            val location =
                locationManager.getLastKnownLocation(provider)
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                val address = Geocoder(this, Locale.ENGLISH).getFromLocation(
                    latitude,
                    longitude,
                    1
                )[0].getAddressLine(0)
                viewModel.getLocation(address, applicationContext)
            }
        }
    }
}