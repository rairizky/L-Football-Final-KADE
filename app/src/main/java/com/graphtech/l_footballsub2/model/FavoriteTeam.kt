package com.graphtech.l_footballsub2.model

data class FavoriteTeam(val id: Long?,
                        val teamId: String?,
                        val teamName: String?,
                        val teamDescription: String?) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
    }
}