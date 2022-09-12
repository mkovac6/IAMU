package hr.algebra.futurama

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.futurama.framework.startActivity

class FuturamaReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity<HostActivity>()
    }
}