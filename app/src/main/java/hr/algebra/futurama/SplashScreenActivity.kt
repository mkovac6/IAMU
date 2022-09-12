package hr.algebra.futurama

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.futurama.databinding.ActivitySplashScreenBinding
import hr.algebra.futurama.framework.startActivity

import hr.algebra.futurama.framework.startAnimation

private const val DELAY = 3000L

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.ivSplash.startAnimation(R.anim.rotate)
        binding.tvSplash.startAnimation(R.anim.blink)
    }

    private fun redirect() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity<HostActivity>()
            }
            , DELAY
        )
    }
}