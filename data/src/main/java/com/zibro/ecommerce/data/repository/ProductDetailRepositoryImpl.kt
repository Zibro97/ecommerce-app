package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.ProductDataSource
import com.zibro.ecommerce.data.db.dao.BasketDao
import com.zibro.ecommerce.data.db.entity.toBasketEntity
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.PI

class ProductDetailRepositoryImpl @Inject constructor(
    private val dataSource : ProductDataSource,
    private val basketDao: BasketDao
) : ProductDetailRepository {
    override fun getProductDetail(productId: String): Flow<Product> {
        return dataSource.getHomeComponents().map { products ->
            products.filterIsInstance<Product>().first { it.productId == productId }
        }
    }

    override suspend fun addBasket(product: Product) {
        val alreadyExistProduct = basketDao.getBasketById(product.productId)

        if (alreadyExistProduct == null) {
            basketDao.insertBasket(product.toBasketEntity())
        } else {
            basketDao.insertBasket(alreadyExistProduct.copy(count = alreadyExistProduct.count + 1))
        }
    }
}