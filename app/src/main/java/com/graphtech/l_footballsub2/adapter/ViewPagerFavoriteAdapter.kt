package com.graphtech.l_footballsub2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.graphtech.l_footballsub2.fragment.MatchFavoriteFragment
import com.graphtech.l_footballsub2.fragment.TeamFavoriteFragment

class ViewPagerFavoriteAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = when(position) {
        0 -> MatchFavoriteFragment()
        1 -> TeamFavoriteFragment()
        else -> null!!
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> "Match Favorite"
        1 -> "Team Favorite"
        else -> null
    }

}