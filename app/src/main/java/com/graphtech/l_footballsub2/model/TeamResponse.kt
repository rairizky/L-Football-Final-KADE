package com.graphtech.l_footballsub2.model


import com.google.gson.annotations.SerializedName


data class TeamResponse(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>
)