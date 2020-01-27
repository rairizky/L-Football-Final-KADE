package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.ResultResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.SearchMatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter (private val view: SearchMatchView, private val apiRepository: ApiRepository,
                       private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearch(query: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val searchMatch = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getSearchData(query)).await(),
                ResultResponse::class.java)

                    view.hideLoading()
                    if (searchMatch.event == null){
                        view.showNothing("Pencarian '${query}' tidak ditemukan.")
                    }else{
                        view.hideNothing()
                        view.showSearchResult(searchMatch.event)
                    }
        }
    }
}