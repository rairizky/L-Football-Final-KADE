package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.KlasemenItem
import com.graphtech.l_footballsub2.presenter.GetBadge
import kotlinx.android.synthetic.main.list_item_klasemen.view.*
import org.jetbrains.anko.toast

class KlasemenAdapter (private val context: Context, private val klasemen: List<KlasemenItem>)
    :RecyclerView.Adapter<KlasemenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_klasemen, parent, false))
    }

    override fun getItemCount(): Int = klasemen.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(klasemen[position])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val teamName = view.tvTeamKlasemen
        private val teamImg = view.imgTeamKlasemen
        private val teamPlayed = view.tvTeamPlayed
        private val teamWin = view.tvTeamWin
        private val teamDraw = view.tvTeamDraw
        private val teamLost = view.tvTeamLoss

        fun bindItems(klasemen: KlasemenItem) {
            teamName.text = klasemen.name
            GetBadge().loadBadge("${klasemen.teamid}", teamImg)
            teamPlayed.text = klasemen.played.toString()
            teamWin.text = klasemen.win.toString()
            teamDraw.text = klasemen.draw.toString()
            teamLost.text = klasemen.loss.toString()
        }

    }
}