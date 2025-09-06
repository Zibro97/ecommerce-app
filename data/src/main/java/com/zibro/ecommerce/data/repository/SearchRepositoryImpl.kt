package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.ProductDataSource
import com.zibro.ecommerce.data.db.dao.SearchDao
import com.zibro.ecommerce.data.db.entity.SearchKeywordEntity
import com.zibro.ecommerce.data.db.entity.toDomain
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchFilter
import com.zibro.ecommerce.domain.model.SearchKeyword
import com.zibro.ecommerce.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val searchDao: SearchDao
) : SearchRepository {
    override suspend fun search(
        searchKeyword: SearchKeyword,
        filters: List<SearchFilter>
    ): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(searchKeyword.keyword))
        return dataSource.getProducts().map { list ->
            list.filter { isAvailableProduct(it, searchKeyword, filters) }
        }
    }

    private fun isAvailableProduct(
        product: Product,
        searchKeyword: SearchKeyword,
        filter: List<SearchFilter>
    ) : Boolean {
        var isAvailable = true
        filter.forEach {
            isAvailable = isAvailable && it.isAvailableProduct(product)
        }
        return isAvailable && product.productName.contains(searchKeyword.keyword)
    }

    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
    }
}