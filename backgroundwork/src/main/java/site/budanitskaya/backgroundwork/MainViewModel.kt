package site.budanitskaya.backgroundwork

import android.app.Application
import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)
    private val viewModelJob = SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    internal fun performWork() {
        viewModelScope.launch {
            while (true) {
                workManager.beginWith(
                    OneTimeWorkRequest.Builder(
                        AvailableMemoryWorker::class.java
                    ).setInitialDelay(10000, TimeUnit.MILLISECONDS)
                        .addTag("11")
                        .build()
                ).then(
                    OneTimeWorkRequest.Builder(
                        BatteryChargeWorker::class.java
                    ).setInitialDelay(10000, TimeUnit.MILLISECONDS)
                        .addTag("11")
                        .build()
                ).enqueue()


                delay(1000 * 60 * 1
                )
            }


        }


    }

    fun cancelWork(){
        viewModelJob.cancel()
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