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
import kotlinx.android.synthetic.main.list_item_search_team.view.*
import org.jetbrains.anko.startActivity

class SearchTeamAdapter (private val context: Context, private val query: List<TeamsItem>)
    : RecyclerView.Adapter<SearchTeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_search_team, parent, false))
    }

    override fun getItemCount(): Int = query.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(query[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName = view.tvTeamNameSearch
        private val teamDesc = view.tvTeamDescSearch
        private val imgTeam = view.imgTeamSearch

        private val item = view.intentTeamSearch

        fun bindItems(query: TeamsItem) {
            GetBadge().loadBadge("${query.idTeam}", imgTeam)
            teamName.text = query.strTeam
            teamDesc.text = query.strDescriptionEN

            item.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>("teamId" to query.idTeam)
            }
        }
    }
}