package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories() : Flow<List<Category>>
}