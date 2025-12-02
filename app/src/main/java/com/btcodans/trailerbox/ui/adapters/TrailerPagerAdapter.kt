package com.btcodans.trailerbox.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.btcodans.trailerbox.ui.NowPlayingFragment
import com.btcodans.trailerbox.ui.PopularFragment
import com.btcodans.trailerbox.ui.UpcomingFragment

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