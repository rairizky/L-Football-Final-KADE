package com.graphtech.l_footballsub2.api

import com.google.gson.Gson
import com.graphtech.l_footballsub2.TestContextProvider
import com.graphtech.l_footballsub2.model.EventsDetailResponse
import com.graphtech.l_footballsub2.model.EventsDetailsItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.EventsDetailPresenter
import com.graphtech.l_footballsub2.view.EventsDetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchEventDetailPresenterTest {
    @Mock
    private lateinit var view: EventsDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: EventsDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventsDetailPresenter(view, apiRepo, gson, TestContextProvider())
    }

    @Test
    fun testDetailEventMatch() {
        val event: MutableList<EventsDetailsItem> = mutableListOf()
        val response = EventsDetailResponse(event)
        val eventId = "602259"

        runBlocking {
            Mockito.`when`(apiRepo.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEventsDetail(eventId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showEventsDetail(event)
        }
    }
}