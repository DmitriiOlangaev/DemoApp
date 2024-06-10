package com.demo.demoapp.core.model

import kotlinx.serialization.Serializable

@Serializable
data class ToDestinationSuggestion(val id: Int, val town: String, val hint: String)
