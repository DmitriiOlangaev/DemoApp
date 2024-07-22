package com.demo.demoapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConcertsResponse(val offers: List<Concert>)