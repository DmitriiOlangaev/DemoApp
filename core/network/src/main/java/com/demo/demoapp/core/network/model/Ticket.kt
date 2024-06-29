package com.demo.demoapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Price,
    @SerialName("provider_name")
    val providerName: String,
    val company: String,
    val departure: DepartureArrival,
    val arrival: DepartureArrival,
    @SerialName("has_transfer")
    val hasTransfer: Boolean,
    @SerialName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    @SerialName("hand_luggage")
    val handLuggage: HandLuggage,
    @SerialName("is_returnable")
    val isReturnable: Boolean,
    @SerialName("is_exchangable")
    val isExchangeable: Boolean
)
