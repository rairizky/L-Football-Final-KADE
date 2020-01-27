package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.EventsMatchItem
import com.graphtech.l_footballsub2.model.ResultItem

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNothing(msg: String)
    fun hideNothing()
    fun showSearchResult(data: List<ResultItem>)
}