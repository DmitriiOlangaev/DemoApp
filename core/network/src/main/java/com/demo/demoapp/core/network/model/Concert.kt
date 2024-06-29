package com.demo.demoapp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Concert(val id: Int, val title: String, val town: String, val price: Price)
