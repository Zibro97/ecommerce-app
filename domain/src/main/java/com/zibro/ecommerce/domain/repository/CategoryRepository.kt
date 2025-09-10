package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories() : Flow<List<Category>>
    fun getProductByCategory(category: Category) : Flow<List<Product>>

    suspend fun likeProduct(product : Product)
}