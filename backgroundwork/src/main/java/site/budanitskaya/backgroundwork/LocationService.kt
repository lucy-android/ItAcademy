package site.budanitskaya.backgroundwork

import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import kotlinx.coroutines.*
import java.util.*

class LocationService : Service() {

    private val job = SupervisorJob()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = makeNotification("Location Info", getLocationInfo(), applicationContext)
        startForeground(1, notification)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    @SuppressLint("MissingPermission")
    fun getLocationInfo(): String {
        var address = ""
        val locationManager =
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
                address = Geocoder(this, Locale.ENGLISH).getFromLocation(
                    latitude,
                    longitude,
                    1
                )[0].getAddressLine(0)
                getLocation(address, applicationContext)
            }
        }
        return address
    }

    private fun getLocation(address: String, context: Context) {
        CoroutineScope(job + Dispatchers.Default).launch {
            while (true) {
                makeNotification("Location Info", address, context)
                delay(1000 * 30)
            }
        }
    }
}