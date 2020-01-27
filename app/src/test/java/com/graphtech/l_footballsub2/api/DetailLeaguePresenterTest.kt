package com.graphtech.l_footballsub2.api

import com.google.gson.Gson
import com.graphtech.l_footballsub2.TestContextProvider
import com.graphtech.l_footballsub2.model.LeaguesItem
import com.graphtech.l_footballsub2.model.LeaguesResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.LeaguesDetailPresenter
import com.graphtech.l_footballsub2.view.LeaguesDetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.*

class DetailLeaguePresenterTest {
    @Mock
    private lateinit var view: LeaguesDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LeaguesDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeaguesDetailPresenter(view, apiRepo, gson, TestContextProvider())
    }

    @Test
    fun testGetDetailLeague() {
        val detail: MutableList<LeaguesItem> = mutableListOf()
        val response = LeaguesResponse(detail)
        val leagueId = "4328"

        runBlocking {
            Mockito.`when`(apiRepo.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeaguesResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeaguesDetail(leagueId)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showLeaguesDetail(detail)
        }
    }
}