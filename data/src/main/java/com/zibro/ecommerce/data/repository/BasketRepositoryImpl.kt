package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.db.dao.BasketDao
import com.zibro.ecommerce.data.db.entity.toDomainModel
import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao
) : BasketRepository {
    override fun getBasketProducts(): Flow<List<BasketProduct>> {
        return basketDao.getAll().map { list ->
            list.map {
                BasketProduct(it.toDomainModel(),it.count)
            }
        }
    }

    override suspend fun removeBasketProduct(product: Product) {
        basketDao.deleteBasket(product.productId)
    }
}