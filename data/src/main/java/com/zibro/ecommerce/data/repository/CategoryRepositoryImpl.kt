package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.ProductDataSource
import com.zibro.ecommerce.data.db.dao.LikeDao
import com.zibro.ecommerce.data.db.entity.toLikeProductEntity
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val likeDao: LikeDao
) : CategoryRepository {
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

    override fun getProductByCategory(category: Category): Flow<List<Product>> {
        return dataSource.getHomeComponents().map { list ->
            list.filterIsInstance<Product>().filter { product ->
                product.category.categoryId == category.categoryId
            }
        }.combine(likeDao.getAll()) { products, likeList ->
            products.map { products -> updateLikeStatus(products, likeList.map {
                it.productId
            }) }
        }
    }

    override suspend fun likeProduct(product: Product) {
        if(product.isLike) {
            likeDao.deleteLike(product.productId)
        } else {
            likeDao.insertLike(product.toLikeProductEntity().copy(isLike = true))
        }
    }

    private fun updateLikeStatus(product: Product, likeProductIds : List<String>) : Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}