package com.demo.demoapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HandLuggage(
    @SerialName("has_hand_luggage") val hasHandLuggage: Boolean,
    val size: String? = null
)
