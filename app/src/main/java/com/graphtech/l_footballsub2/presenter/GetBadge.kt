package com.graphtech.l_footballsub2.presenter

import android.widget.ImageView
import com.google.gson.Gson
import com.graphtech.l_footballsub2.network.ApiRepository

class GetBadge {
    private lateinit var presenter : BadgePresenter

    fun loadBadge(id: String, image: ImageView){
        val apiRepository = ApiRepository()
        val gson = Gson()

        presenter = BadgePresenter(image, apiRepository, gson)
        presenter.getLogo(id)
    }
}