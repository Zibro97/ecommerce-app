package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    operator fun invoke() : Flow<List<Category>> {
        return categoryRepository.getCategories()
    }
}