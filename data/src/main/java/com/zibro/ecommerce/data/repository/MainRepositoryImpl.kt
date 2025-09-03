package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.ProductDataSource
import com.zibro.ecommerce.domain.model.BaseModel
import com.zibro.ecommerce.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource
) : MainRepository {

    override fun getModelList(): Flow<List<BaseModel>> = dataSource.getHomeComponents()
}