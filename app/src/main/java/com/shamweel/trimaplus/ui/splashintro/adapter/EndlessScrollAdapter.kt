package com.shamweel.trimaplus.ui.splashintro.adapter

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EndlessScrollAdapter constructor(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    private val items: List<Fragment>
) : FragmentStateAdapter(fm, lifeCycle) {

    override fun createFragment(position: Int): Fragment = items[position.rem(items.size)]

    override fun getItemCount(): Int = if (items.isEmpty()) 0 else Int.MAX_VALUE

    // This override is crucial so the adapter can handle using same fragments over and over again.
    override fun getItemId(position: Int): Long = position.rem(items.size).toLong()

    fun getCenterPage(position: Int = 0): Int {
        val middle = Integer.MAX_VALUE / 2
        return middle - position.rem(items.size) + 1 + position
    }
}

fun ViewPager2.setupEndlessScrollAdapter(
    activity: FragmentActivity,
    list: List<Fragment>
) {
    val endlessAdapter = EndlessScrollAdapter(
        activity.supportFragmentManager,
        activity.lifecycle,
        list
    )
    adapter = endlessAdapter
    setCurrentItem(endlessAdapter.getCenterPage(), false)
}

inline fun ViewPager2.setupEndlessScrollAdapter(
    activity: FragmentActivity,
    block: () -> List<Fragment>
) = setupEndlessScrollAdapter(activity, block())

fun ViewPager2.setupAutomaticScroll(lifeCycle: Lifecycle, delayMillis: Long = 10_000) {
    lifeCycle.coroutineScope.launch {
        repeat(Int.MAX_VALUE) {
            delay(delayMillis)
            setCurrentItemEx(currentItem + 1, duration = 1_100, pagePxToDrag = height)
        }
    }
}

// setCurrentItem with a slower animation
// https://stackoverflow.com/questions/57505875/change-viewpager2-scroll-speed-when-sliding-programmatically/59235979#59235979
fun ViewPager2.setCurrentItemEx(
    item: Int,
    duration: Long,
    interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
    pagePxToDrag: Int = width // Default value taken from getWidth() from ViewPager2 view,
) {
    val pxToDrag: Int = pagePxToDrag * (item - currentItem)
    val animator = ValueAnimator.ofInt(0, pxToDrag)
    var previousValue = 0
    animator.addUpdateListener { valueAnimator ->
        val currentValue = valueAnimator.animatedValue as Int
        val currentPxToDrag = (currentValue - previousValue).toFloat()
        fakeDragBy(-currentPxToDrag)
        previousValue = currentValue
    }
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) { beginFakeDrag() }
        override fun onAnimationEnd(animation: Animator?) { endFakeDrag() }
        override fun onAnimationCancel(animation: Animator?) { /* Ignored */ }
        override fun onAnimationRepeat(animation: Animator?) { /* Ignored */ }
    })
    animator.interpolator = interpolator
    animator.duration = duration
    animator.start()
}


