package site.budanitskaya.backgroundwork

import android.app.ActivityManager
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters

class AvailableMemoryWorker(private val ctx: Context, params: WorkerParameters) :
    Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        val memoryInfo = ActivityManager.MemoryInfo()
        val activityManager =
            getSystemService(ctx, ActivityManager::class.java)
        activityManager!!.getMemoryInfo(memoryInfo)
        val availableMegs: Double = (memoryInfo.availMem / 1048576).toDouble()

        return try {
            makeNotification("Memory Info", "Available Memory = $availableMegs MB", appContext)
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}