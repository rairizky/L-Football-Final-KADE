package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.activity.DetailTeamActivity
import com.graphtech.l_footballsub2.adapter.FavoriteMatchAdapter
import com.graphtech.l_footballsub2.helper.database
import com.graphtech.l_footballsub2.model.FavoriteTeam
import kotlinx.android.synthetic.main.fragment_match_favorite.*
import kotlinx.android.synthetic.main.fragment_team_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamFavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
    }

    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteMatchAdapter(this.requireContext(), favorites) {
            startActivity<DetailTeamActivity>("teamId" to it.teamId)
        }

        rvFavoriteTeam.layoutManager = LinearLayoutManager(context)
        rvFavoriteTeam.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
