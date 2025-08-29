package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    fun getCategory() : Flow<List<Category>> {
        return categoryRepository.getCategories()
    }

    fun getProductByCategory(category: Category) : Flow<List<Product>> {
        return categoryRepository.getProductByCategory(category = category)
    }
}