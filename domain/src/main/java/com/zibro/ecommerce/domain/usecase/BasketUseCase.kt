package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {
    fun getBasketProducts() : Flow<List<BasketProduct>> {
        return basketRepository.getBasketProducts()
    }

    suspend fun removeBasketProducts(product: Product) {
        basketRepository.removeBasketProduct(product)
    }
}