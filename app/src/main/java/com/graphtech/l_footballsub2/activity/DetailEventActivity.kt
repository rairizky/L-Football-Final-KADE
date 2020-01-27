package com.graphtech.l_footballsub2.activity

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.helper.database
import com.graphtech.l_footballsub2.model.EventsDetailsItem
import com.graphtech.l_footballsub2.model.Favorite
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.EventsDetailPresenter
import com.graphtech.l_footballsub2.presenter.GetBadge
import com.graphtech.l_footballsub2.view.EventsDetailView
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*


class DetailEventActivity : AppCompatActivity(), EventsDetailView {

    private val getString = "eventId"

    lateinit var eventPresenter: EventsDetailPresenter

    //setdata
    private var eventId: String? = null
    private var dtEvent: String? = null
    private var homeTeam: String? = null
    private var homeScore: String? = null
    private var awayTeam: String? = null
    private var awayScore: String? = null

    //tablayout
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false


    override fun showLoading() {
        supportActionBar?.hide()
        shimmerEvents.startShimmer()
        layoutDetailEvent.visibility = View.GONE
    }

    override fun hideLoading() {
        supportActionBar?.show()
        shimmerEvents.stopShimmer()
        shimmerEvents.visibility = View.GONE
        layoutDetailEvent.visibility = View.VISIBLE
    }

    override fun showEventsDetail(eventDetail: List<EventsDetailsItem>) {
        rlEvent.isRefreshing = false
        val eventsData = eventDetail[0]

        //add data
        eventId = eventsData.idEvent
        dtEvent = eventsData.dateEvent
        homeTeam = eventsData.strHomeTeam
        homeScore = eventsData.intHomeScore
        awayTeam = eventsData.strAwayTeam
        awayScore = eventsData.intAwayScore

        //date
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val convertDate = date.parse(eventsData.dateEvent)
        val resultDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(convertDate)
        eventDate.text = resultDate

        //event Detail
        eventHomeTeam.text = eventsData.strHomeTeam
        eventAwayTeam.text = eventsData.strAwayTeam

        //image team
        GetBadge().loadBadge("${eventsData.idHomeTeam}", eventHomeBadge)
        GetBadge().loadBadge("${eventsData.idAwayTeam}", eventAwayBadge)

        //event score
        if (eventsData.intHomeScore == null && eventsData.intAwayScore == null){
            eventHomeScore.text = "-"
            eventAwayScore.text = "-"
        }else{
            eventHomeScore.text = eventsData.intHomeScore
            eventAwayScore.text = eventsData.intAwayScore
        }

        //event player goal
        eventHomeGoal.text = eventsData.strHomeGoalDetails?.replace(";", "\n")
        eventAwayGoal.text = eventsData.strAwayGoalDetails?.replace(";", "\n")

        //yellow card
        eventHomeYellowCard.text = eventsData.strHomeYellowCards?.replace(";", "\n")
        eventAwayYellowCard.text = eventsData.strAwayYellowCards?.replace(";", "\n")

        //yellow card
        eventHomeRedCard.text = eventsData.strHomeRedCards?.replace(";", "\n")
        eventAwayRedCard.text = eventsData.strAwayRedCards?.replace(";", "\n")

        //event keeper
        eventHomeKeeper.text = eventsData.strHomeLineupGoalkeeper?.replace("; ", "")
        eventAwayKeeper.text = eventsData.strAwayLineupGoalkeeper?.replace("; ", "")

        //event def
        eventHomeDefend.text = eventsData.strHomeLineupDefense?.replace("; ", "\n")
        eventAwayDefend.text = eventsData.strAwayLineupDefense?.replace("; ", "\n")

        //event midlane
        eventHomeMid.text = eventsData.strHomeLineupMidfield?.replace("; ", "\n")
        eventAwayMid.text = eventsData.strAwayLineupMidfield?.replace("; ", "\n")

        //event forward
        eventHomeForward.text = eventsData.strHomeLineupForward?.replace("; ", "\n")
        eventAwayForward.text = eventsData.strAwayLineupForward?.replace("; ", "\n")

        //event subtitution
        eventHomeSubtitutes.text = eventsData.strHomeLineupSubstitutes?.replace("; ", "\n")
        eventAwaySubtitutes.text = eventsData.strAwayLineupSubstitutes?.replace("; ", "\n")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        //get eventId
        val eventId = intent.getStringExtra(getString)

        supportActionBar?.title = getString(R.string.appNameHeader)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteState(eventId)

        //set presenter
        val apiRepository = ApiRepository()
        val gson = Gson()

        eventPresenter = EventsDetailPresenter(this, apiRepository, gson)
        eventPresenter.getEventsDetail(eventId)

        rlEvent.onRefresh {
            shimmerEvents.visibility = View.VISIBLE
            shimmerEvents.startShimmer()
            eventPresenter.getEventsDetail(eventId)
            shimmerEvents.stopShimmer()
            shimmerEvents.visibility = View.GONE
        }

    }

    private fun favoriteState(id: String) {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to eventId,
                    Favorite.DATE_EVENT to dtEvent,
                    Favorite.HOME_TEAM to homeTeam,
                    Favorite.SCORE_HOME to homeScore,
                    Favorite.AWAY_TEAM to awayTeam,
                    Favorite.SCORE_AWAY to awayScore)
            }
            toast("Added to favorite.")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try{
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to "${eventId}")
            }
            toast("Removed from favorites.")
        }catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }
}
