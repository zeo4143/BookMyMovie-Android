@file:Suppress("DEPRECATION")

package com.example.bookmymovie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter( fm: FragmentManager) : FragmentPagerAdapter(fm)
{

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = LiveMovies()
            1 -> fragment = MyBookings()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "Live"
            1 -> title = "My Bookings"
        }

        return title
    }
}