package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.FavoriteTeam
import com.graphtech.l_footballsub2.presenter.GetBadge
import kotlinx.android.synthetic.main.list_item_favorite_match.view.*

class FavoriteMatchAdapter (private val context: Context,
                            private val teams: List<FavoriteTeam>,
                            private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_favorite_match, parent, false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bindItems(teams[position], listener)
    }

}

class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgTeam = view.imgTeamFavoriteImage
    private val teamName = view.tvTeamFavoriteName
    private val teamDesc = view.tvTeamFavoriteDesc
    private val intentTeam = view.intentFavoriteTeam

    fun bindItems(teams: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        GetBadge().loadBadge("${teams.teamId}", imgTeam)
        teamName.text = teams.teamName
        teamDesc.text = teams.teamDescription

        intentTeam.setOnClickListener { listener(teams) }
    }
}
