package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.TeamAdapter
import com.graphtech.l_footballsub2.model.TeamsItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.TeamPresenter
import com.graphtech.l_footballsub2.view.TeamView
import kotlinx.android.synthetic.main.fragment_klasemen.*
import kotlinx.android.synthetic.main.fragment_team.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {

    companion object {
        const val ID = "leagueId"

        fun newInstance(leagueId: String): TeamFragment {
            val fragment = TeamFragment()
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
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    private var teams: MutableList<TeamsItem> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: TeamPresenter

    override fun showLoading() {
        shimmerTeam.startShimmer()
    }

    override fun hideLoading() {
        shimmerTeam.stopShimmer()
        shimmerTeam.visibility = View.GONE
    }

    override fun showTeam(data: List<TeamsItem>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueId = arguments?.getString(ID)

        //lets code!
        val apiRepo = ApiRepository()
        val gson = Gson()

        //setRv
        rvTeam.layoutManager = LinearLayoutManager(context)
        adapter = TeamAdapter(this.requireContext(), teams)
        rvTeam.adapter = adapter
        presenter = TeamPresenter(this, apiRepo, gson)
        presenter.getTeams(leagueId.toString())
    }


}
