package com.dicoding.emergencyapp.ui.news

import android.content.Context
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.ui.news.entertainment.EntertainmentFragment
import com.dicoding.emergencyapp.ui.news.health.HealthFragment
import com.dicoding.emergencyapp.ui.news.science.ScienceFragment

class SectionsPagerAdapter(private val mContext: Context?,fm: FragmentManager): FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.health,R.string.science,R.string.entertainment)
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HealthFragment()
            1 -> ScienceFragment()
            2 -> EntertainmentFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext?.resources?.getString(TAB_TITLES[position])
    }

}