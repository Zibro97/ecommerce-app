package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchFilter
import com.zibro.ecommerce.domain.model.SearchKeyword
import com.zibro.ecommerce.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun search(keyword : SearchKeyword, filters : List<SearchFilter>) : Flow<List<Product>> {
        return searchRepository.search(keyword).map { list ->
            list.filter { isAvailableProduct(it, keyword, filters) }
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

    fun getSearchKeywords() : Flow<List<SearchKeyword>> {
        return searchRepository.getSearchKeywords()
    }

    suspend fun likeProduct(product: Product) {
        searchRepository.likeProduct(product)
    }
}