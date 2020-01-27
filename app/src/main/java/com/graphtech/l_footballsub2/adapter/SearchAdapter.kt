package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.activity.DetailEventActivity
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.ResultItem
import kotlinx.android.synthetic.main.list_item_result.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter (private val context: Context, private val query: List<ResultItem>)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_result, parent, false))
    }

    override fun getItemCount(): Int = query.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(query[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val eventDate = view.eventDate
        private val eventMatch = view.eventMatch
        private val eventHomeTeam = view.eventHomeTeam
        private val eventHomeScore = view.eventHomeScore

        private val eventAwayTeam = view.eventAwayTeam
        private val eventAwayScore = view.eventAwayScore

        val detailEvent = view.detailEvent

        fun bindItems(query: ResultItem) {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val convertDate = date.parse(query.dateEvent)
            val resultDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(convertDate).toString()

            eventDate.text = resultDate
            eventMatch.text = query.strEvent
            eventHomeTeam.text = query.strHomeTeam
            //score null
            if (query.intHomeScore == null && query.intAwayScore == null){
                eventHomeScore.text = "-"
                eventAwayScore.text = "-"
            }else{
                eventHomeScore.text = query.intHomeScore.toString()
                eventAwayScore.text = query.intAwayScore.toString()
            }
            eventAwayTeam.text = query.strAwayTeam

            //item intent
            detailEvent.setOnClickListener {
                itemView.context.startActivity<DetailEventActivity>("eventId" to query.idEvent)
            }
        }
    }
}