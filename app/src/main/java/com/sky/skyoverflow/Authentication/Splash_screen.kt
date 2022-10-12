package com.sky.skyoverflow.Authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sky.skyoverflow.MainActivity
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.databinding.ActivitySplashScreenBinding


class Splash_screen : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed(
            Runnable {
                if (MyPreferences.getInstance(applicationContext).getBoolean(PrefConf.PREF_SEASON,false)) {
                    startActivity(Intent(this@Splash_screen, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@Splash_screen, Login::class.java))
                    finish()
                }
            }, 1500
        )

    }
}