package com.shamweel.trimaplus.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.shamweel.trimaplus.data.AppDataStore
import com.shamweel.trimaplus.databinding.ActivityHomeBinding
import com.shamweel.trimaplus.ui.extensions.startNewActivity
import com.shamweel.trimaplus.ui.splashintro.activity.SplashIntroActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var appDataStore: AppDataStore

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