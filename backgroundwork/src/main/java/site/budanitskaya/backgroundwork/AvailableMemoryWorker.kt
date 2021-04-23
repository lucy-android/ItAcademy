package site.budanitskaya.backgroundwork

import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
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
        return try {

            makeNotification("Available Memory = ${memoryInfo.availMem}", appContext)

            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}