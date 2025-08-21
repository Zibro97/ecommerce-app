package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke() : Flow<List<Product>> {
        return mainRepository.getProductList()
    }
}