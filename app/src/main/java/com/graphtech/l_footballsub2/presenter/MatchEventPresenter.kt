package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.EventsMatchResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.MatchEventView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchEventPresenter (private val view: MatchEventView, private val apiRepository: ApiRepository,
                           private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {


    fun getPastEvent(leagueId : String){
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val eventPast = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getPastEvent(leagueId)).await(),
                EventsMatchResponse::class.java)

                    view.hideLoading()
                    view.showEventMatch(eventPast.events)
        }
    }

    fun getNextEvent(leagueId : String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val eventNext = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getNextEvent(leagueId)).await(),
                EventsMatchResponse::class.java)

                    view.hideLoading()
                    view.showEventMatch(eventNext.events)
        }
    }
}