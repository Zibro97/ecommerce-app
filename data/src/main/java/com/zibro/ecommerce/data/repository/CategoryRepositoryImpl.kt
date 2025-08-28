package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override fun getCategories(): Flow<List<Category>> = flow {
        emit(listOf(
            Category.Top,
            Category.Pants,
            Category.Shoes,
            Category.Bag,
            Category.Dress,
            Category.FashionAccessories,
            Category.Outer,
            Category.Skirt
        ))
    }
}