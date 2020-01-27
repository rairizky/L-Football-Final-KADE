package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.League
import com.graphtech.l_footballsub2.activity.DetailLeagueActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_league.view.*
import org.jetbrains.anko.startActivity

class LeagueAdapter (private val context: Context, private val items: List<League>)
    :RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_league, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val detailNameLeague = view.item_detail_league_name
        private val detailImageLeague = view.item_detail_league_image
        private val detailDescriptionLeague = view.item_detail_league_description

        //intentDetail
        private val itemDetail = view.findViewById<LinearLayout>(R.id.item_detail)

        fun bindItem(items: League){
            detailNameLeague.text = items.leagueName
            items.leagueImage?.let { Picasso.get().load(it).fit().into(detailImageLeague) }
            detailDescriptionLeague.text = items.leagueDescription

            //intent
            itemDetail.setOnClickListener {
                itemView.context.startActivity<DetailLeagueActivity>("items" to items)
            }
        }
    }

}