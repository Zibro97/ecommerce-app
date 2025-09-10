package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.datasource.ProductDataSource
import com.zibro.ecommerce.data.db.dao.LikeDao
import com.zibro.ecommerce.data.db.entity.toLikeProductEntity
import com.zibro.ecommerce.domain.model.BaseModel
import com.zibro.ecommerce.domain.model.Carousel
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val likeDao: LikeDao
) : MainRepository {

    override fun getModelList(): Flow<List<BaseModel>> {
        return dataSource.getHomeComponents().combine(likeDao.getAll()) { homeComponents, likeList ->
            homeComponents.map { model ->
                mappingLike(model, likeList.map {
                    it.productId
                })
            }
        }
    }

    override suspend fun likeProduct(product: Product) {
        likeDao.insertLike(product.toLikeProductEntity())
    }

    private fun mappingLike(baseModel: BaseModel, likeProductIds : List<String>) : BaseModel {
        return when(baseModel) {
            is Carousel -> { baseModel.copy(productList = baseModel.productList.map { updateLikeStatus(it, likeProductIds) }) }
            is Ranking -> { baseModel.copy(productList = baseModel.productList.map { updateLikeStatus(it, likeProductIds) }) }
            is Product -> { updateLikeStatus(baseModel, likeProductIds) }
            else -> baseModel
        }
    }

    private fun updateLikeStatus(product: Product, likeProductIds : List<String>) : Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}