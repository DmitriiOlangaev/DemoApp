package com.demo.demoapp.domain.model

data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: DepartureArrival,
    val arrival: DepartureArrival,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangeable: Boolean
)
