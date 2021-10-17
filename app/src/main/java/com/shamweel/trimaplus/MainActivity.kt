package com.shamweel.trimaplus

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.shamweel.trimaplus.data.AppDataStore
import com.shamweel.trimaplus.databinding.ActivityMainBinding
import com.shamweel.trimaplus.ui.splashintro.utils.setAppLocale
import com.shamweel.trimaplus.ui.splashintro.utils.startNewActivity
import com.shamweel.trimaplus.ui.home.HomeActivity
import com.shamweel.trimaplus.ui.splashintro.activity.SplashIntroActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDataStore: AppDataStore

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ContextWrapper(newBase?.setAppLocale("in")))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDataStore = AppDataStore(this)
        appDataStore.userLoggedIn.asLiveData().observe(this, Observer {
            val activity =
                if (it == true) HomeActivity::class.java else SplashIntroActivity::class.java
            startNewActivity(activity)
        })

    }
}