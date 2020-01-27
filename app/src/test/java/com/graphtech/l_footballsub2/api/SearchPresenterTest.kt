package com.graphtech.l_footballsub2.api

import com.google.gson.Gson
import com.graphtech.l_footballsub2.TestContextProvider
import com.graphtech.l_footballsub2.model.ResultItem
import com.graphtech.l_footballsub2.model.ResultResponse
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.SearchPresenter
import com.graphtech.l_footballsub2.view.SearchMatchView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepo: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepo, gson, TestContextProvider())
    }

    @Test
    fun testSearchMatch() {
        val search: MutableList<ResultItem> = mutableListOf()
        val response = ResultResponse(search)
        val query = "Arsenal"

        runBlocking {
            Mockito.`when`(apiRepo.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResultResponse::class.java
                )
            ).thenReturn(response)

            presenter.getSearch(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showSearchResult(search)
        }
    }
}