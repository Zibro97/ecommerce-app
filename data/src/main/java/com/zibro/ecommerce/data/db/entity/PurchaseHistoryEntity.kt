package com.zibro.ecommerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.PurchaseHistory
import java.time.ZonedDateTime

@Entity(tableName = "history")
data class PurchaseHistoryEntity(
    val basketList : List<BasketProduct>,
    val purchaseAt : ZonedDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

}

fun PurchaseHistoryEntity.toDomainModel() =
    PurchaseHistory(
        basketList = basketList,
        purchaseDate = purchaseAt
    )

fun PurchaseHistory.toEntity() =
    PurchaseHistoryEntity(
        basketList = basketList,
        purchaseAt = purchaseDate
    )