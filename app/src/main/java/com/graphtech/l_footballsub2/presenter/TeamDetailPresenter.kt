package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.TeamResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.TeamDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter (private val view: TeamDetailView,
                           private val apiRepo: ApiRepository,
                           private val gson: Gson,
                           private val contextPool: CoroutineContextProvider = CoroutineContextProvider())
{
    fun getDetailTeam(teamId: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val detailTeam = gson.fromJson(apiRepo.doRequest(TheSportDbApi.getDetailTeam(teamId)).await(),
                TeamResponse::class.java)

            view.hideLoading()
            view.showTeamDetail(detailTeam.teams)
        }
    }
}
