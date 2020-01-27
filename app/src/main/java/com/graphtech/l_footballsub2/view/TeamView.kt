package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.TeamsItem

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeam(data: List<TeamsItem>)
}