package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.PastEventAdapter
import com.graphtech.l_footballsub2.model.EventsMatchItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.MatchEventPresenter
import com.graphtech.l_footballsub2.view.MatchEventView
import kotlinx.android.synthetic.main.fragment_prev_match.*

/**
 * A simple [Fragment] subclass.
 */
class PrevMatchFragment : Fragment(), MatchEventView{

    companion object {
        private val ID = "leagueId"

        fun newInstance(leagueId: String): PrevMatchFragment {
            val fragment = PrevMatchFragment()
            val args = Bundle()
            args.putString(ID, leagueId)
            fragment.arguments = args
            return fragment
        }
    }

    //declare
    private var pastEvent: MutableList<EventsMatchItem> = mutableListOf()
    private lateinit var adapterPast: PastEventAdapter
    private lateinit var pastPresenter: MatchEventPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    override fun showLoading() {
        shimmerEvent.startShimmer()
    }

    override fun hideLoading() {
        shimmerEvent.stopShimmer()
        shimmerEvent.visibility = View.GONE
    }

    override fun showEventMatch(data: List<EventsMatchItem>) {
        pastEvent.clear()
        pastEvent.addAll(data)
        adapterPast.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get League id
        val leagueId = arguments?.getString(ID)

        val apiRepository = ApiRepository()
        val gson = Gson()


        rvPastEvent.layoutManager = LinearLayoutManager(context)
        adapterPast = PastEventAdapter(this.requireContext(), pastEvent)
        rvPastEvent.adapter = adapterPast
        pastPresenter = MatchEventPresenter(this, apiRepository, gson)
        pastPresenter.getPastEvent(leagueId.toString())
    }


}
