package hr.algebra.futurama

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.futurama.api.FuturamaFetcher
import hr.algebra.futurama.framework.sendBroadcast
import hr.algebra.futurama.framework.setBooleanPreference

private const val JOB_ID = 1

class FuturamaService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {

        FuturamaFetcher(this).fetchItems()
    }

    companion object {
        fun enqueue(context: Context, intent: Intent) {
            enqueueWork(context, FuturamaService::class.java, JOB_ID, intent)
        }
    }
}