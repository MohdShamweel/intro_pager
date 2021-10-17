package com.shamweel.trimaplus.ui.splashintro.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.shamweel.trimaplus.R
import com.shamweel.trimaplus.data.network.Resource
import com.shamweel.trimaplus.data.responses.Data
import com.shamweel.trimaplus.databinding.LayoutIntroBinding
import com.shamweel.trimaplus.ui.splashintro.adapter.ScreenSlidePagerAdapter
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroFirstFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroFourthFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroSecondFragment
import com.shamweel.trimaplus.ui.splashintro.fragment.IntroThirdFragment
import java.util.*


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.snackbar(message: String, action: (() -> Unit?)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction(context.getString(R.string.string_retry)) {
            it()
        }
    }
    snackbar.show()
}


fun Fragment.handleAPIError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError ->
            requireView().snackbar(
                getString(R.string.no_internet),
                retry
            )

        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}

fun Activity.fullscreen() {
    with(WindowInsetsControllerCompat(window, window.decorView)) {
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
        hide(WindowInsetsCompat.Type.systemBars())
    }
}

fun Activity.exitFullscreen() {
    WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
}

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun LayoutIntroBinding.setViews(data: Data) {
    txtTitle.text = data.title
    txtDesc.text = data.desc
    animationView.setAnimationFromUrl(data.pic)
    animationView.addLottieOnCompositionLoadedListener {
        progressbar.visible(false)
    }
}

fun LayoutIntroBinding.setNoInternet(){
    animationView.setAnimation("no_internet.json")
}

fun Context.setAppLocale(language: String): Context {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return createConfigurationContext(config)
}

fun ViewPager2.getAdapterLocally(adapter: ScreenSlidePagerAdapter, context: Context): ScreenSlidePagerAdapter {
    adapter.addFragment(IntroFirstFragment())
    adapter.addFragment(IntroSecondFragment())
    adapter.addFragment(IntroThirdFragment())
    adapter.addFragment(IntroFourthFragment())
    return adapter

}