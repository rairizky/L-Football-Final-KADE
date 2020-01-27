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
import com.graphtech.l_footballsub2.model.FavoriteTeam
import com.graphtech.l_footballsub2.model.TeamsItem
import com.graphtech.l_footballsub2.network.ApiRepository
import com.graphtech.l_footballsub2.presenter.GetBadge
import com.graphtech.l_footballsub2.presenter.TeamDetailPresenter
import com.graphtech.l_footballsub2.view.TeamDetailView
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), TeamDetailView {

    companion object {
        const val getId = "teamId"
    }

    //set data
    private var teamFavId: String? = null
    private var teamFavName: String? = null
    private var teamFavDesc: String? = null

    //tablayout
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    lateinit var presenter: TeamDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        //get
        val teamId = intent.getStringExtra(getId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //check is fav
        favoriteState(teamId)

        //set presenter
        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, apiRepository, gson)
        presenter.getDetailTeam(teamId)
    }

    override fun showLoading() {
        detailView.visibility = View.GONE
        shimmerDetailTeam.startShimmer()
        supportActionBar?.hide()
    }

    override fun hideLoading() {
        shimmerDetailTeam.stopShimmer()
        shimmerDetailTeam.visibility = View.GONE
        detailView.visibility = View.VISIBLE
        supportActionBar?.show()
    }

    override fun showTeamDetail(data: List<TeamsItem>) {
        val data = data[0]

        teamFavId = data.idTeam
        teamFavName = data.strTeam
        teamFavDesc = data.strDescriptionEN

        //bind to view
        GetBadge().loadBadge("${data.idTeam}", imgDetailTeam)
        tvDetailTeamName.text = data.strTeam
        tvDetailTeamLeague.text = data.strLeague
        tvDetailTeamYear.text = data.intFormedYear
        tvDetailTeamCountry.text = data.strCountry
        tvDetailTeamStadium.setText("${data.strStadium} (${data.strStadiumLocation})")
        tvDetailTeamDesc.text = data.strDescriptionEN
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
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

    private fun favoriteState(id: String) {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to teamFavId,
                    FavoriteTeam.TEAM_NAME to teamFavName,
                    FavoriteTeam.TEAM_DESCRIPTION to teamFavDesc)
            }
            toast("Added to Favorite.")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to "${teamFavId}")
            }
            toast("Removed from favorites.")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }
}
