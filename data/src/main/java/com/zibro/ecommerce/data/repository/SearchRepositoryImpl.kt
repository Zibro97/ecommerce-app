package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.ProductDataSource
import com.zibro.ecommerce.data.db.dao.LikeDao
import com.zibro.ecommerce.data.db.dao.SearchDao
import com.zibro.ecommerce.data.db.entity.SearchKeywordEntity
import com.zibro.ecommerce.data.db.entity.toDomain
import com.zibro.ecommerce.data.db.entity.toLikeProductEntity
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchFilter
import com.zibro.ecommerce.domain.model.SearchKeyword
import com.zibro.ecommerce.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val searchDao: SearchDao,
    private val likeDao: LikeDao
) : SearchRepository {
    override suspend fun search(
        searchKeyword: SearchKeyword,
    ): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(searchKeyword.keyword))
        return dataSource.getProducts().combine(likeDao.getAll()) { products, likeList ->
            products.map { products -> updateLikeStatus(products, likeList.map {
                it.productId
            }) }
        }
    }

    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
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