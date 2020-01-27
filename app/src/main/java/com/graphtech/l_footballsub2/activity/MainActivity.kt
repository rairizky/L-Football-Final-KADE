package com.graphtech.l_footballsub2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.LeagueAdapter
import com.graphtech.l_footballsub2.model.League
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //placeholder shimmer start
        shimmerLayout.startShimmer()
        shimmerLayout.stopShimmer()
        shimmerLayout.visibility = View.GONE
        initData()


        intentFavorite.setOnClickListener {
            startActivity<FavoriteActivity>()
        }

        supportActionBar?.hide()
    }

    private fun initData(){
        val idLeague = resources.getStringArray(R.array.league_id)
        val nameLeague = resources.getStringArray(R.array.league_name)
        val imageLeague = resources.obtainTypedArray(R.array.league_logo)
        val descriptionLeague = resources.getStringArray(R.array.league_description)
        items.clear()
        for (i in nameLeague.indices) {
            items.add(
                League(idLeague[i],
                    nameLeague[i],
                    imageLeague.getResourceId(i,0),
                    descriptionLeague[i]
                )
            )
        }
        imageLeague.recycle()

        //recyclerView
        rvLeague.layoutManager = LinearLayoutManager(this)
        rvLeague.adapter = LeagueAdapter(this, items)
    }
}
