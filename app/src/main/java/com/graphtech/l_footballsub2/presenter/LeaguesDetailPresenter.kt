package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.LeaguesResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.LeaguesDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguesDetailPresenter(private val view: LeaguesDetailView, private val apiRepository: ApiRepository,
                             private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeaguesDetail(leagueId: String){
        view.showLoading()

        GlobalScope.launch(contextPool.main){
            val dataLeague = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getLeaguesDetail(leagueId)).await(),
                LeaguesResponse::class.java)

            view.hideLoading()
            view.showLeaguesDetail(dataLeague.leagues)
        }
    }
}