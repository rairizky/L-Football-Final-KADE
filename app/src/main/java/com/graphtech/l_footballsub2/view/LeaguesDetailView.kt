package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.LeaguesItem

interface LeaguesDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeaguesDetail(dataLeagueTeam: List<LeaguesItem>)
}