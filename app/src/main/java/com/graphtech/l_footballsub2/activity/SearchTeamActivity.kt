package com.graphtech.l_footballsub2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.SearchTeamAdapter
import com.graphtech.l_footballsub2.model.TeamsItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.SearchTeamPresenter
import com.graphtech.l_footballsub2.view.SearchTeamView
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {

    private var search: MutableList<TeamsItem> = mutableListOf()
    private lateinit var adapter: SearchTeamAdapter
    private lateinit var presenter: SearchTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        supportActionBar?.hide()

        rvSearchTeam.layoutManager = LinearLayoutManager(this)
        adapter = SearchTeamAdapter(this, search)
        rvSearchTeam.adapter = adapter
        search.clear()
        shimmerSearchTeam.visibility = View.GONE

        //searchView
        searchTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                shimmerSearchTeam.visibility = View.VISIBLE
                shimmerSearchTeam.startShimmer()

                search.clear()
                setPresenter(newText.toString())

                shimmerSearchTeam.stopShimmer()
                shimmerSearchTeam.visibility = View.GONE

                return true
            }
        })
    }

    private fun setPresenter(text: String) {
        val apiRepo = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, apiRepo, gson)
        presenter.getSearchTeam(text)
        search.clear()
    }

    override fun showLoading() {
        shimmerSearchTeam.startShimmer()
    }

    override fun hideLoading() {
        shimmerSearchTeam.stopShimmer()
        shimmerSearchTeam.visibility = View.GONE
    }

    override fun showNothing(msg: String) {
        tvNothingSearch.text = msg
        tvNothingSearch.visibility = View.VISIBLE
    }

    override fun hideNothing() {
        tvNothingSearch.visibility = View.GONE
    }

    override fun showSearch(data: List<TeamsItem>) {
        search.clear()
        data.forEach {
            if (it.strSport.equals("Soccer")) {
                search.add(it)
            }else {
                tvNothingSearch.visibility = View.VISIBLE
                tvNothingSearch.text = "Team tidak ada."
                search.clear()
            }
        }
        adapter.notifyDataSetChanged()
    }
}
