package com.demo.demoapp.domain.model

import android.graphics.drawable.Drawable

data class Concert(
    val id: Int,
    val drawable: Drawable,
    val title: String,
    val town: String,
    val price: Price
)
