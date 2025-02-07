package com.demo.demoapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Luggage(@SerialName("has_luggage") val hasLuggage: Boolean, val price: Price? = null)
