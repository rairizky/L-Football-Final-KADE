package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.activity.DetailTeamActivity
import com.graphtech.l_footballsub2.model.TeamsItem
import com.graphtech.l_footballsub2.presenter.GetBadge
import kotlinx.android.synthetic.main.list_item_team.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class TeamAdapter (private val context: Context, private val teams: List<TeamsItem>)
    :RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_team, parent, false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(teams[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName = view.tvTeamName
        private val teamImg = view.imgTeamImage
        private val teamDesc = view.tvTeamDesc

        private val intentTeam = view.intentTeam

        fun bindItems(team: TeamsItem) {
            teamName.text = team.strTeam
            GetBadge().loadBadge("${team.idTeam}", teamImg)
            teamDesc.text = team.strDescriptionEN

            //click item
            intentTeam.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>("teamId" to "${team.idTeam}")
            }
        }
    }
}