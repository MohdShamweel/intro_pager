package com.shamweel.trimaplus.ui.home

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.utils.Utils
import com.shamweel.trimaplus.data.AppDataStore
import com.shamweel.trimaplus.databinding.ActivityHomeBinding
import com.shamweel.trimaplus.ui.splashintro.utils.startNewActivity
import com.shamweel.trimaplus.ui.splashintro.activity.SplashIntroActivity
import com.shamweel.trimaplus.ui.splashintro.utils.wrap
import kotlinx.coroutines.launch
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var appDataStore: AppDataStore

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(wrap(this, Locale("in")))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        appDataStore = AppDataStore(this)

        binding.btnLogout.setOnClickListener {
           performLogout()
        }
    }

    private fun performLogout() = lifecycleScope.launch {
        appDataStore.clear()
        startNewActivity(SplashIntroActivity::class.java)
    }
}