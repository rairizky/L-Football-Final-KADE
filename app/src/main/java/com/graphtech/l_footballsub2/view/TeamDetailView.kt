package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.TeamsItem

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamsItem>)
}