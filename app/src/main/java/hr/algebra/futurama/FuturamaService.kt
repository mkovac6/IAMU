package hr.algebra.futurama

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.futurama.framework.sendBroadcast
import hr.algebra.futurama.framework.setBooleanPreference

private const val JOB_ID = 1

class FuturamaService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        Thread.sleep(6000)
        setBooleanPreference(DATA_IMPORTED, true)
        sendBroadcast<FuturamaReceiver>()
    }

    companion object {
        fun enqueue(context: Context, intent: Intent) {
            enqueueWork(context, FuturamaService::class.java, JOB_ID, intent)
        }
    }
}