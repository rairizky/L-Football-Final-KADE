package com.graphtech.l_footballsub2.presenter

import android.widget.ImageView
import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.TeamResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BadgePresenter(private val view: ImageView, private val apiRepository: ApiRepository,
                     private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()){


    fun getLogo(teamId: String){
        GlobalScope.launch(contextPool.main) {
            val image = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getBadge(teamId)).await(),
                TeamResponse::class.java)

            Picasso.get().load(image.teams[0].strTeamBadge).into(view)

        }
    }
}