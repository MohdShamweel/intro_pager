package com.shamweel.trimaplus.ui.splashintro.activity

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.shamweel.trimaplus.data.AppDataStore
import com.shamweel.trimaplus.databinding.ActivitySplashIntroBinding
import com.shamweel.trimaplus.ui.extensions.getAdapterLocally
import com.shamweel.trimaplus.ui.extensions.setAppLocale
import com.shamweel.trimaplus.ui.extensions.startNewActivity
import com.shamweel.trimaplus.ui.home.HomeActivity
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroFirstFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroFourthFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroSecondFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroThirdFragment
import com.shamweel.trimaplus.ui.splashintro.view.FadeOutTransformer
import com.shamweel.trimaplus.ui.splashintro.adapter.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class SplashIntroActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashIntroBinding
    private lateinit var adapter: ScreenSlidePagerAdapter
    private lateinit var appDataStore: AppDataStore
    var page: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        appDataStore = AppDataStore(this)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        with(binding.viewpager) {
            adapter = ScreenSlidePagerAdapter(this@SplashIntroActivity)
            adapter = getAdapterLocally(adapter as ScreenSlidePagerAdapter, context)
            setPageTransformer(FadeOutTransformer())
            offscreenPageLimit = adapter!!.itemCount
        }

        binding.dotsIndicator.setViewPager2(binding.viewpager)
        autoScrollViewPager()

        binding.btnLogin.setOnClickListener {
            launchHomeActivity()
        }
        binding.btnRegister.setOnClickListener {
            launchHomeActivity()
        }
    }

    private fun launchHomeActivity() {
        lifecycleScope.launch { appDataStore.setLogin(true) }
        startNewActivity(HomeActivity::class.java)
    }

    private fun autoScrollViewPager() {
        Handler(Looper.getMainLooper()).postDelayed(mUpdateResults, 3_000)
    }

    private val mUpdateResults = Runnable {
        val numPages: Int = binding.viewpager.adapter!!.itemCount
        page = (page + 1) % numPages
        binding.viewpager.currentItem = page
        autoScrollViewPager()
    }
}