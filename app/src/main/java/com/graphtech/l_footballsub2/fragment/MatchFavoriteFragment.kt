package com.graphtech.l_footballsub2.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.activity.DetailEventActivity
import com.graphtech.l_footballsub2.adapter.FavoriteAdapter
import com.graphtech.l_footballsub2.helper.database
import com.graphtech.l_footballsub2.model.Favorite
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.fragment_match_favorite.*
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class MatchFavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_favorite, container, false)
    }

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteAdapter(this.requireContext(), favorites) {
            startActivity<DetailEventActivity>("eventId" to it.eventId)
        }

        rvFavorite.layoutManager = LinearLayoutManager(context)
        rvFavorite.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }

    }

}
