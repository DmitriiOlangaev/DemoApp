package com.demo.demoapp.core.model

import kotlinx.serialization.Serializable

@Serializable
data class DepartureArrival(val town: String, val date: String, val airport: String)
