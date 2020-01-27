package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.KlasemenAdapter
import com.graphtech.l_footballsub2.model.KlasemenItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.KlasemenPresenter
import com.graphtech.l_footballsub2.view.KlasemenView
import kotlinx.android.synthetic.main.fragment_klasemen.*

/**
 * A simple [Fragment] subclass.
 */
class KlasemenFragment : Fragment(), KlasemenView {

    companion object {
        const val ID = "leagueId"

        fun newInstance(leagueId: String): KlasemenFragment {
            val fragment = KlasemenFragment()
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
        return inflater.inflate(R.layout.fragment_klasemen, container, false)
    }

    private var klasemen: MutableList<KlasemenItem> = mutableListOf()
    private lateinit var adapter: KlasemenAdapter
    private lateinit var presenter: KlasemenPresenter

    override fun showLoading() {
        shimmerKlasemen.startShimmer()
    }

    override fun hideLoading() {
        shimmerKlasemen.stopShimmer()
        shimmerKlasemen.visibility = View.GONE
    }

    override fun showKlasemen(data: List<KlasemenItem>) {
        klasemen.clear()
        klasemen.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueId = arguments?.getString(ID)

        //lets code!

        val apiRepo = ApiRepository()
        val gson = Gson()

        //set rv
        rvKlasemen.layoutManager = LinearLayoutManager(context)
        adapter = KlasemenAdapter(this.requireContext(), klasemen)
        rvKlasemen.adapter = adapter
        presenter = KlasemenPresenter(this, apiRepo, gson)
        presenter.getKlasemen(leagueId.toString())
    }


}
