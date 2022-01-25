package com.riezki.latihan.moviecatalogdb

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.riezki.latihan.moviecatalogdb.ui.main.MovieFragment
import com.riezki.latihan.moviecatalogdb.ui.main.TvFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        @StringRes
        private val tabTitles = intArrayOf(R.string.movie, R.string.tv)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvFragment()
            else -> fragment as Fragment
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }
}