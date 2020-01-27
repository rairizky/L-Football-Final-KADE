package com.graphtech.l_footballsub2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.graphtech.l_footballsub2.R
import com.graphtech.l_footballsub2.adapter.FavoriteAdapter
import com.graphtech.l_footballsub2.adapter.ViewPagerFavoriteAdapter
import com.graphtech.l_footballsub2.helper.database
import com.graphtech.l_footballsub2.model.Favorite
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//
//        rlFavorite.onRefresh {
//            favorites.clear()
//            showFavorite()
//        }

        setTabLayout()
    }

    private fun setTabLayout() {
        val adapter = ViewPagerFavoriteAdapter(supportFragmentManager)
        viewPagerFavorite.adapter = adapter
        menufavorite.setupWithViewPager(viewPagerFavorite)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
