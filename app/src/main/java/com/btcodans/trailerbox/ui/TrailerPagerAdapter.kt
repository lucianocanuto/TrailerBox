package com.btcodans.trailerbox.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TrailerPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularFragment()
            1 -> NowPlayingFragment()
            else -> UpcomingFragment()
        }
    }
}