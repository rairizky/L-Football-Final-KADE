package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.TeamResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.SearchTeamView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (private val view: SearchTeamView,
                           private val apiRepo: ApiRepository,
                           private val gson: Gson,
                           private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getSearchTeam(query: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val resultTeam = gson.fromJson(apiRepo.doRequest(TheSportDbApi.getSearchTeam(query)).await(),
                TeamResponse::class.java)

            view.hideLoading()
            if (resultTeam.teams == null) {
                view.showNothing("Pencarian '${query}' tidak ditemukan.")
            }else{
                view.hideNothing()
                view.showSearch(resultTeam.teams)
            }
        }
    }
}