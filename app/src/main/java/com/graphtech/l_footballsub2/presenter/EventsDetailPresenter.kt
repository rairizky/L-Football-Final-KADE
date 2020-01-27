package com.graphtech.l_footballsub2.presenter

import com.google.gson.Gson
import com.graphtech.l_footballsub2.model.EventsDetailResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.network.TheSportDbApi
import com.graphtech.l_footballsub2.utils.CoroutineContextProvider
import com.graphtech.l_footballsub2.view.EventsDetailView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventsDetailPresenter (private val view: EventsDetailView, private val apiRepository: ApiRepository,
                             private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventsDetail(eventId: String) {
        view.showLoading()

        GlobalScope.launch(contextPool.main) {
            val eventDetail = gson.fromJson(apiRepository.doRequest(TheSportDbApi.getDetailEvent(eventId)).await(),
                EventsDetailResponse::class.java)

                view.hideLoading()
                view.showEventsDetail(eventDetail.events)
        }
    }
}