package com.graphtech.l_footballsub2.model


import com.google.gson.annotations.SerializedName

data class KlasemenResponse(

	@field:SerializedName("table")
	val table: List<KlasemenItem>
)