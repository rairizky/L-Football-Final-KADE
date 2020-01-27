package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.LeaguesItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.LeaguesDetailPresenter
import com.graphtech.l_footballsub2.view.LeaguesDetailView
import kotlinx.android.synthetic.main.fragment_detail_league.*

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment(), LeaguesDetailView{

    companion object {
        const val ID = "leagueId"

        fun newInstance(leagueId: String): DetailLeagueFragment {
            val fragment = DetailLeagueFragment()
            val args = Bundle()
            args.putString(ID, leagueId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_league, container, false)
    }

    override fun showLoading() {
        shimmerDesc.startShimmer()
        layoutDesc.visibility = View.GONE
    }

    override fun hideLoading() {
        shimmerDesc.stopShimmer()
        shimmerDesc.visibility = View.GONE
        layoutDesc.visibility = View.VISIBLE
    }

    override fun showLeaguesDetail(dataLeagueTeam: List<LeaguesItem>) {
        detailDesc.text = dataLeagueTeam[0].strDescriptionEN
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueId = arguments?.getString(ID)

        //set Presenter
        val apiRepository = ApiRepository()
        val gson = Gson()
        val presenter = LeaguesDetailPresenter(this, apiRepository, gson)
        presenter.getLeaguesDetail(leagueId.toString())
    }

}
