package com.zibro.ecommerce.domain.model

import java.time.ZonedDateTime

data class PurchaseHistory(
    val basketList : List<BasketProduct>,
    val purchaseDate : ZonedDateTime
)
