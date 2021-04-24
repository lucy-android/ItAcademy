package site.budanitskaya.backgroundwork

import android.app.Application
import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)

    internal fun performWork() {

        viewModelScope.launch {

            while (true) {
                workManager.beginWith(
                    OneTimeWorkRequest.Builder(
                        AvailableMemoryWorker::class.java
                    ).setInitialDelay(10000, TimeUnit.MILLISECONDS)
                        .build()
                ).then(
                    OneTimeWorkRequest.Builder(
                        BatteryChargeWorker::class.java
                    ).setInitialDelay(10000, TimeUnit.MILLISECONDS)
                        .build()
                ).enqueue()

/*                workManager.cancelAllWork()*/

                delay(1000 * 60 * 10
                )
            }


        }


    }
    fun getLocation(address: String, context: Context){
        viewModelScope.launch {
            while (true){
                makeNotification(address, context)
                delay(1000 * 30)
            }
        }
    }
}