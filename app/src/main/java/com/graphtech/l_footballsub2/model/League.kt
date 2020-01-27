package com.graphtech.l_footballsub2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    val leagueId: String?,
    val leagueName: String?,
    val leagueImage: Int?,
    val leagueDescription: String?
) : Parcelable