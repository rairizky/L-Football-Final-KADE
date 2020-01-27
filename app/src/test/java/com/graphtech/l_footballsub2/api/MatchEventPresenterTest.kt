package com.graphtech.l_footballsub2.api

import com.google.gson.Gson
import com.graphtech.l_footballsub2.TestContextProvider
import com.graphtech.l_footballsub2.model.EventsMatchItem
import com.graphtech.l_footballsub2.model.EventsMatchResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.MatchEventPresenter
import com.graphtech.l_footballsub2.view.MatchEventView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchEventPresenterTest {
    @Mock
    private lateinit var view: MatchEventView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchEventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchEventPresenter(view, apiRepo, gson, TestContextProvider())
    }

    @Test
    fun testPastMatchEvent() {
        val match: MutableList<EventsMatchItem> = mutableListOf()
        val response = EventsMatchResponse(match)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepo.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPastEvent(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showEventMatch(match)
        }
    }

    @Test
    fun testNextMatchEvent() {
        val match: MutableList<EventsMatchItem> = mutableListOf()
        val response = EventsMatchResponse(match)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepo.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsMatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextEvent(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showEventMatch(match)
        }
    }

}