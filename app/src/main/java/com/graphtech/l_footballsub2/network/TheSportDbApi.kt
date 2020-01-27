package com.graphtech.l_footballsub2.network

import com.graphtech.l_footballsub2.BuildConfig

object TheSportDbApi {
    fun getLeaguesDetail(leagueId: String): String {
        return BuildConfig.BASE_URL+"lookupleague.php?id="+leagueId
    }

    fun getPastEvent(leagueId: String): String {
        return BuildConfig.BASE_URL+"eventspastleague.php?id="+leagueId
    }

    fun getNextEvent(leagueId: String): String{
        return BuildConfig.BASE_URL+"eventsnextleague.php?id="+leagueId
    }

    fun getDetailEvent(eventId: String): String {
        return BuildConfig.BASE_URL+"lookupevent.php?id="+eventId
    }

    fun getBadge(teamId: String): String {
        return BuildConfig.BASE_URL+"lookupteam.php?id="+teamId
    }

    fun getSearchData(querySearch: String): String {
        return BuildConfig.BASE_URL+"searchevents.php?e="+querySearch
    }

    fun getKlasemen(leagueId: String): String {
        return BuildConfig.BASE_URL+"lookuptable.php?l="+leagueId
    }

    fun getTeam(leagueId: String): String {
        return BuildConfig.BASE_URL+"lookup_all_teams.php?id="+leagueId
    }

    fun getDetailTeam(teamId: String): String {
        return BuildConfig.BASE_URL+"lookupteam.php?id="+teamId
    }

    fun getSearchTeam(query: String): String {
        return BuildConfig.BASE_URL+"searchteams.php?t="+query
    }
}