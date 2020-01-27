package com.graphtech.l_footballsub2.model


import com.google.gson.annotations.SerializedName


data class EventsDetailResponse(

	@field:SerializedName("events")
	val events: List<EventsDetailsItem>
)