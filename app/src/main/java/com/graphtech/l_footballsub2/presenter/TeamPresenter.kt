package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.TeamResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.TeamView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter (private val view: TeamView,
                     private val apiRepo: ApiRepository,
                     private val gson: Gson,
                     private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeams(leagueId: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val teams = gson.fromJson(apiRepo.doRequest(TheSportDbApi.getTeam(leagueId)).await(),
                TeamResponse::class.java)

            view.hideLoading()
            view.showTeam(teams.teams)
        }
    }

}