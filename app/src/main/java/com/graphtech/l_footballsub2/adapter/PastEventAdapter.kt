package com.graphtech.l_footballsub2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.graphtech.l_footballsub2.activity.DetailEventActivity
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.model.EventsMatchItem
import kotlinx.android.synthetic.main.list_item_past_event.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class PastEventAdapter (private val context: Context?, private val event: List<EventsMatchItem>)
    : RecyclerView.Adapter<PastEventAdapter.EventPast>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventPast {
        return EventPast(LayoutInflater.from(context).inflate(R.layout.list_item_past_event, parent, false))
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: EventPast, position: Int) {
        holder.bindItems(event[position])
    }

    class EventPast(view: View) : RecyclerView.ViewHolder(view){
        private val eventDate = view.eventDate
        private val eventMatch = view.eventMatch
        //private val eventHomeBadge = view.eventHomeBadge
        private val eventHomeTeam = view.eventHomeTeam
        private val eventHomeScore = view.eventHomeScore
        //private val eventAwayBadge = view.eventAwayBadge
        private val eventAwayTeam = view.eventAwayTeam
        private val eventAwayScore = view.eventAwayScore

        val detailEvent = view.detailEvent

        fun bindItems(event: EventsMatchItem) {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val convertDate = date.parse(event.dateEvent)
            val resultDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(convertDate).toString()

            //bind data
            eventDate.text = resultDate

            //event match
            eventMatch.text = event.strEvent

            //home
            eventHomeTeam.text = event.strHomeTeam
            eventHomeScore.text = event.intHomeScore.toString()
            //away
            eventAwayTeam.text = event.strAwayTeam
            eventAwayScore.text = event.intAwayScore.toString()


            //item intent
            detailEvent.setOnClickListener {
                itemView.context.startActivity<DetailEventActivity>("eventId" to event.idEvent)
            }
        }
    }

}