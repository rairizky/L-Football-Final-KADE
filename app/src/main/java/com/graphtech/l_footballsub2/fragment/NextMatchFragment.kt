package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.NextEventAdapter
import com.graphtech.l_footballsub2.model.EventsMatchItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.MatchEventPresenter
import com.graphtech.l_footballsub2.view.MatchEventView
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(), MatchEventView {
    companion object {
        const val ID = "leagueId"

        fun newInstance(leagueId: String): NextMatchFragment {
            val fragment = NextMatchFragment()
            val args = Bundle()
            args.putString(ID, leagueId)
            fragment.arguments = args
            return fragment
        }
    }

    //declare
    private var nextEvent: MutableList<EventsMatchItem> = mutableListOf()
    private lateinit var adapterNext: NextEventAdapter
    private lateinit var nextPresenter: MatchEventPresenter


    override fun showLoading() {
        shimmerEvent.startShimmer()
    }

    override fun hideLoading() {
        shimmerEvent.stopShimmer()
        shimmerEvent.visibility = View.GONE
    }

    override fun showEventMatch(data: List<EventsMatchItem>) {
        nextEvent.clear()
        nextEvent.addAll(data)
        adapterNext.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get id
        val leagueId = arguments?.getString(ID)

        val apiRepository = ApiRepository()
        val gson = Gson()


        rvNextEvent.layoutManager = LinearLayoutManager(context)
        adapterNext = NextEventAdapter(this.requireContext(), nextEvent)
        rvNextEvent.adapter = adapterNext
        nextPresenter = MatchEventPresenter(this, apiRepository, gson)
        nextPresenter.getNextEvent(leagueId.toString())
    }
}
