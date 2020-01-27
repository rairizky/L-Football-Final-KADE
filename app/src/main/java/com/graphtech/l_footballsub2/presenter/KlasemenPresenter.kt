package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.KlasemenResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.KlasemenView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KlasemenPresenter (private val view: KlasemenView,
                         private val apiRepo: ApiRepository,
                         private val gson: Gson,
                         private val contextPool: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getKlasemen(leagueId: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val klasemen = gson.fromJson(apiRepo.doRequest(TheSportDbApi.getKlasemen(leagueId)).await(),
                KlasemenResponse::class.java)

            view.hideLoading()
            view.showKlasemen(klasemen.table)
        }
    }
}