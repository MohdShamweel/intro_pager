package com.shamweel.trimaplus.ui.splashintro.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shamweel.trimaplus.databinding.ActivitySplashIntroBinding
import com.shamweel.trimaplus.ui.splashintro.adapter.ScreenSlidePagerAdapter
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroFirstFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroFourthFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroSecondFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroThirdFragment
import com.shamweel.trimaplus.ui.splashintro.view.FadeOutTransformer

class SplashIntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashIntroBinding
    private lateinit var adapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        //this.fullscreen()

        binding.viewpager.adapter = createViewPagerAdapter()
        binding.viewpager.setPageTransformer(FadeOutTransformer())
        binding.dotsIndicator.setViewPager2(binding.viewpager)
    }

    private fun createViewPagerAdapter(): ScreenSlidePagerAdapter {
        adapter = ScreenSlidePagerAdapter(this)
        adapter.addFragment(IntroFirstFragment())
        adapter.addFragment(IntroSecondFragment())
        adapter.addFragment(IntroThirdFragment())
        adapter.addFragment(IntroFourthFragment())
        return adapter
    }

}