package com.graphtech.l_footballsub2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.SearchAdapter
import com.graphtech.l_footballsub2.model.ResultItem
import com.graphtech.l_footballsub2.presenter.SearchPresenter
import com.graphtech.l_footballsub2.view.SearchMatchView
import kotlinx.android.synthetic.main.activity_search_result.*
import android.widget.SearchView
import com.google.gson.Gson
import com.graphtech.l_footballsub2.network.ApiRepository


class SearchResultActivity : AppCompatActivity(), SearchMatchView {


    private var search: MutableList<ResultItem> = mutableListOf()

    private lateinit var adapter: SearchAdapter
    private lateinit var searchPresenter: SearchPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        //toolbar
        supportActionBar?.hide()


        rvResult.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter(this, search)
        rvResult.adapter = adapter
        search.clear()
        shimmerSearch.visibility = View.GONE


        searchMatch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                shimmerSearch.visibility = View.VISIBLE
                shimmerSearch.startShimmer()

                search.clear()
                setPresenter(newText.toString())

                shimmerSearch.stopShimmer()
                shimmerSearch.visibility = View.GONE

                return true
            }

        })

        btnBackFromResult.setOnClickListener {
            super.onBackPressed()
        }

    }

    fun setPresenter(text: String){
        val apiRepository = ApiRepository()
        val gson = Gson()
        searchPresenter = SearchPresenter(this, apiRepository, gson)
        searchPresenter.getSearch(text)
        search.clear()
    }

    override fun showLoading() {
        shimmerSearch.startShimmer()
    }

    override fun hideLoading() {
        shimmerSearch.stopShimmer()
        shimmerSearch.visibility = View.GONE
    }

    override fun showSearchResult(data: List<ResultItem>) {
        search.clear()
        data.forEach {
            if (it.strSport.equals("Soccer")){
                search.add(it)
            }else{
                tvNothing.visibility = View.VISIBLE
                tvNothing.text = "Match tidak ada."
                search.clear()
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun showNothing(msg: String) {
        tvNothing.text = msg
        tvNothing.visibility = View.VISIBLE
    }

    override fun hideNothing() {
        tvNothing.visibility = View.GONE
    }
}
