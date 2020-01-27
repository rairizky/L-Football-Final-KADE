package com.graphtech.l_footballsub2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.League
import com.graphtech.l_footballsub2.model.LeaguesItem
import com.graphtech.l_footballsub2.presenter.LeaguesDetailPresenter
import com.graphtech.l_footballsub2.view.LeaguesDetailView
import kotlinx.android.synthetic.main.activity_detail_league.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.graphtech.l_footballsub2.adapter.ViewPagerAdapter
import com.graphtech.l_footballsub2.network.ApiRepository
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast


class DetailLeagueActivity : AppCompatActivity(), LeaguesDetailView{

    lateinit var presenter: LeaguesDetailPresenter

    //model intent parceabable
    private lateinit var leagueData : League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        //get parceable intent
        leagueData = intent.getParcelableExtra("items") as League

        supportActionBar?.hide()

        //set presenter
        setPresenter(leagueData)

        //set after refresh
        swipeDetail.onRefresh {
            shimmerDetail.startShimmer()
            setPresenter(leagueData)
            shimmerDetail.stopShimmer()
        }

        //setting TabLayout
        setTablayout(leagueData)

        //back
        btnBackFromDetail.setOnClickListener {
            super.onBackPressed()
        }


        //set spinner search
        val menuSearch = arrayOf("Search", "Match", "Team")
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuSearch)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerSearch.adapter = adapterSpinner
        spinnerSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent?.getItemAtPosition(position).toString() == "Match"){
                    startActivity<SearchResultActivity>()
                }else if (parent?.getItemAtPosition(position).toString() == "Team") {
                    startActivity<SearchTeamActivity>()
                }
            }

        }


    }

    fun setPresenter(item: League){
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = LeaguesDetailPresenter(this, apiRepository, gson)
        presenter.getLeaguesDetail(item.leagueId.toString())
    }

    fun setTablayout(item: League) {
        val adapter = ViewPagerAdapter(supportFragmentManager, item.leagueId.toString())
        viewPagerEvent.adapter = adapter
        tabLayoutEvent.setupWithViewPager(viewPagerEvent)
    }

    //show shimmer loading
    override fun showLoading() {
        shimmerDetail.startShimmer()
    }

    //hide shimmer loading
    override fun hideLoading() {
        shimmerDetail.stopShimmer()
        shimmerDetail.visibility = View.GONE
        detailLayout.visibility = View.VISIBLE
    }

    //show detail from api to view
    override fun showLeaguesDetail(dataLeagueTeam: List<LeaguesItem>) {
        //set data
        swipeDetail.isRefreshing = false
        resultName.text = dataLeagueTeam[0].strLeague
        Glide.with(this).load(leagueData.leagueImage).into(resultImage)
        resultCountry.text = dataLeagueTeam[0].strCountry
        resultFormedyear.text = dataLeagueTeam[0].intFormedYear
        resultDescription.text = dataLeagueTeam[0].strSport
    }
}
