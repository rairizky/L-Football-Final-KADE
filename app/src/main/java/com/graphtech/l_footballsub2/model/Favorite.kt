package com.graphtech.l_footballsub2.model

data class Favorite(val id: Long?,
                    val eventId: String?,
                    val dateEvent: String?,
                    val homeTeam: String?,
                    val scoreHome: String?,
                    val awayTeam: String?,
                    val scoreAway: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val SCORE_HOME: String = "SCORE_HOME"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val SCORE_AWAY: String = "SCORE_AWAY"
    }
}