package com.trimaplus.ui.splashintro.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.trimaplus.R
import com.trimaplus.data.AppDataStore
import com.trimaplus.databinding.ActivitySplashIntroBinding
import com.trimaplus.ui.splashintro.utils.startNewActivity
import com.trimaplus.ui.home.HomeNavActivity
import com.trimaplus.ui.splashintro.adapter.*
import com.trimaplus.ui.splashintro.utils.NetworkUtils
import com.trimaplus.ui.splashintro.adapter.ScreenSlidePagerAdapter
import com.trimaplus.ui.splashintro.utils.getAdapterLocally
import com.trimaplus.ui.splashintro.view.FadeOutTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        //autoScrollViewPager()

        binding.btnLogin.setOnClickListener {
            launchHomeActivity()
        }
        binding.btnRegister.setOnClickListener {
            launchHomeActivity()
        }
    }

    private fun launchHomeActivity() {
        if (!NetworkUtils.isNetworkConnected(this)){
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch { appDataStore.setLogin(true) }
        startNewActivity(HomeNavActivity::class.java)
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