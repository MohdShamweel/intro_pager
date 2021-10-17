package com.shamweel.trimaplus.ui.splashintro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val mFragmentList: MutableList<Fragment> = ArrayList()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment = mFragmentList[position]

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }
}