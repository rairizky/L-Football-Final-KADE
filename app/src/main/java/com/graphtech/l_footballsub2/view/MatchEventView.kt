package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.EventsMatchItem

interface MatchEventView {
    fun showLoading()
    fun hideLoading()
    fun showEventMatch(data: List<EventsMatchItem>)

}