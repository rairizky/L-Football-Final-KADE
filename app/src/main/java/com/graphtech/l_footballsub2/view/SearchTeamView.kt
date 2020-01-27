package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.TeamsItem

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showNothing(msg: String)
    fun hideNothing()
    fun showSearch(data: List<TeamsItem>)
}