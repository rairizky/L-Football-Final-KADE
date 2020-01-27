package com.graphtech.l_footballsub2.model

import com.google.gson.annotations.SerializedName

data class KlasemenItem(

	@field:SerializedName("loss")
	val loss: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("goalsfor")
	val goalsfor: Int? = null,

	@field:SerializedName("goalsagainst")
	val goalsagainst: Int? = null,

	@field:SerializedName("teamid")
	val teamid: String? = null,

	@field:SerializedName("goalsdifference")
	val goalsdifference: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("draw")
	val draw: Int? = null,

	@field:SerializedName("played")
	val played: Int? = null,

	@field:SerializedName("win")
	val win: Int? = null
)