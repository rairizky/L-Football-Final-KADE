package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.EventsDetailsItem

interface EventsDetailView {
    fun showLoading()
    fun hideLoading()
    fun showEventsDetail(eventDetail: List<EventsDetailsItem>)

}