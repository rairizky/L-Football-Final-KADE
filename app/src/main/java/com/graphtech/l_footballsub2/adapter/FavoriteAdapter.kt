package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.Favorite
import kotlinx.android.synthetic.main.list_item_favorite.view.*
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter (private val context: Context, private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_favorite, parent, false))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val eventDate = view.favoriteEventDate
    private val homeTeam = view.FavoriteEventHomeTeam
    private val homeScore = view.FavoriteEventHomeScore
    private val awayTeam = view.FavoriteEventAwayTeam
    private val awayScore = view.FavoriteEventAwayScore

    private val detailFavorite = view.detailFavorite

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val convertDate = date.parse(favorite.dateEvent)
        val resultDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(convertDate).toString()

        eventDate.text = resultDate
        homeTeam.text = favorite.homeTeam
        homeScore.text = favorite.scoreHome
        awayTeam.text = favorite.awayTeam
        awayScore.text = favorite.scoreAway

        detailFavorite.setOnClickListener { listener(favorite) }
    }
}