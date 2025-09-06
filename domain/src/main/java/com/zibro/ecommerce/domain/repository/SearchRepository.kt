package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchFilter
import com.zibro.ecommerce.domain.model.SearchKeyword
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(
        searchKeyword : SearchKeyword,
        filters : List<SearchFilter>
    ) : Flow<List<Product>>

    fun getSearchKeywords() : Flow<List<SearchKeyword>>
}