package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository : LikeRepository
) {
    operator fun invoke() : Flow<List<Product>> = repository.getLikeProduct()
}