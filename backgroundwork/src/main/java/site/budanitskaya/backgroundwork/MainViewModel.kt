package site.budanitskaya.backgroundwork

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val workManager = WorkManager.getInstance(application)

    internal fun performWork() {
        workManager.enqueue(
            PeriodicWorkRequest.Builder(
                BatteryChargeWorker::class.java,
                30,
                TimeUnit.MINUTES
            )
                .build())
    }
}