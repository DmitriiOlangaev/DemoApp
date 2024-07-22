package com.demo.demoapp.domain.model

import com.demo.demoapp.data.model.Price

data class Price(val value: Int) {
    constructor(price: Price) : this(price.value)
}
