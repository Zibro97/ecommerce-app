package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchKeyword
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(searchKeyword : SearchKeyword) : Flow<List<Product>>

    fun getSearchKeywords() : Flow<List<SearchKeyword>>
}