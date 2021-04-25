package site.budanitskaya.backgroundwork

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import org.json.JSONException
import org.json.JSONObject
import site.budanitskaya.backgroundwork.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var locationManager: LocationManager

    val MY_PERMISSIONS_REQUEST_LOCATION = 99

    private fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {

                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        binding.showUserLocation.visibility = View.GONE

                        //Request location updates:
                        locationManager =
                            getSystemService(Context.LOCATION_SERVICE) as LocationManager

/*        makeNotification(location.toString(), applicationContext)*/

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

                                val x = Geocoder(this, Locale.ENGLISH).getFromLocation(
                                    latitude,
                                    longitude,
                                    1
                                )[0].getAddressLine(0)
                                viewModel.getLocation(x, applicationContext)
                                makeNotification(x, applicationContext)
                            }
                        }
                    }
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the ViewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.showBatteryCharge.setOnClickListener{
            if(binding.showBatteryCharge.isChecked){
                viewModel.performWork()
            } else if(!binding.showBatteryCharge.isChecked) {
                viewModel.cancelWork()
            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            binding.showUserLocation.visibility = View.GONE}


/*        myServiceIntent.putExtra(inputExtra, "546")*/


        binding.showUserLocation.setOnClickListener{
/*            if(binding.showUserLocation.isChecked){
                Manifest.permission.ACCESS_FINE_LOCATION.di
            }*/
            checkLocationPermission()
        }

/*        ContextCompat.startForegroundService(this, myServiceIntent)*/


        locationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager

/*        makeNotification(location.toString(), applicationContext)*/

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

                val x = Geocoder(this, Locale.ENGLISH).getFromLocation(
                    latitude,
                    longitude,
                    1
                )[0].getAddressLine(0)
                viewModel.getLocation(x, applicationContext)
                makeNotification(x, applicationContext)
            }

        }

/*    fun startService() {


    }


    fun stopService() {
        val serviceIntent = Intent(this, LocationService::class.java)
        stopService(serviceIntent)
    }*/
    }

    override fun onResume() {
        super.onResume()

    }

    fun isPermissionGranted(): Boolean {
        return (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
                == PackageManager.PERMISSION_GRANTED)
    }

}