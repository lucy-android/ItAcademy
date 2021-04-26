package site.budanitskaya.backgroundwork

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class BatteryChargeWorker(private val ctx: Context, params: WorkerParameters) :
    Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        return try {
            val batteryStatus: Intent? =
                IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
                    ctx.registerReceiver(null, ifilter)
                }
            val batteryPct: Float? = batteryStatus?.let { intent ->
                val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                level * 100 / scale.toFloat()
            }
            makeNotification("Battery Info", "${batteryPct.toString()} %", appContext)
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}