package com.graphtech.l_footballsub2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.graphtech.l_footballsub2.fragment.*

class ViewPagerAdapter (fm: FragmentManager, private val leagueId: String) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = when(position) {
        0 -> DetailLeagueFragment.newInstance(leagueId)
        1 -> PrevMatchFragment.newInstance(leagueId)
        2 -> NextMatchFragment.newInstance(leagueId)
        3 -> KlasemenFragment.newInstance(leagueId)
        4 -> TeamFragment.newInstance(leagueId)
        else -> null!!
    }

    override fun getCount(): Int = 5

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> "Detail League"
        1 -> "Prev Match"
        2 -> "Next Match"
        3 -> "Standings"
        4 -> "Team"
        else -> null
    }

}