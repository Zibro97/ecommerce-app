package com.zibro.ecommerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zibro.ecommerce.data.db.converter.BasketConverter
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Price
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Shop

@Entity(tableName = "basket")
@TypeConverters(BasketConverter::class)
data class BasketProductEntity(
    @PrimaryKey
    val productId : String,
    val productName : String,
    val imageUrl : String,
    val price : Price,
    val category: Category,
    val shop : Shop,
    val isNew : Boolean,
    val isFreeShipping : Boolean
)

fun BasketProductEntity.toDomainModel() : Product =
    Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )