package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    fun getBasketProducts() : Flow<List<BasketProduct>>

    suspend fun removeBasketProduct(product: Product)

    suspend fun checkBasket(products : List<BasketProduct>)
}