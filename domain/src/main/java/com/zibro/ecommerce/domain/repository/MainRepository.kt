package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.BaseModel
import com.zibro.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getModelList() : Flow<List<BaseModel>>

    suspend fun likeProduct(product: Product)
}