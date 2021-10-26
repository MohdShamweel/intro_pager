package com.trimaplus.ui.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.trimaplus.data.AppDataStore
import com.trimaplus.databinding.ActivityHomeBinding
import com.trimaplus.ui.splashintro.utils.startNewActivity
import com.trimaplus.ui.splashintro.activity.SplashIntroActivity
import com.trimaplus.ui.splashintro.utils.wrap
import kotlinx.coroutines.launch
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var appDataStore: AppDataStore

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(wrap(newBase, Locale("in")))
        } ?: {
            super.attachBaseContext(newBase)
        }
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