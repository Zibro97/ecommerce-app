package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProductList() : Flow<List<Product>>
}