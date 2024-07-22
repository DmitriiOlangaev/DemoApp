package com.demo.demoapp.domain.model


data class TicketOffer(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Price
)
