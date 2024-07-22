package com.demo.demoapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketOffer(
    val id: Int,
    val title: String,
    @SerialName("time_range") val timeRange: List<String>,
    val price: Price
)
