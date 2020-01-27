package com.graphtech.l_footballsub2.view

import com.graphtech.l_footballsub2.model.KlasemenItem

interface KlasemenView {
    fun showLoading()
    fun hideLoading()
    fun showKlasemen(data: List<KlasemenItem>)
}