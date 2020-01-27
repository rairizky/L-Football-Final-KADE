package com.graphtech.l_footballsub2.model


import com.google.gson.annotations.SerializedName


data class ResultResponse(

	@field:SerializedName("event")
	val event: List<ResultItem>
)