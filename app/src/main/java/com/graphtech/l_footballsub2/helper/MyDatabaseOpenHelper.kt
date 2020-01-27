package com.graphtech.l_footballsub2.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.graphtech.l_footballsub2.model.Favorite
import com.graphtech.l_footballsub2.model.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.DATE_EVENT to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.SCORE_HOME to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.SCORE_AWAY to TEXT)

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_DESCRIPTION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

//access property
val Context.database : MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)