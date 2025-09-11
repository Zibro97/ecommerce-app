package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.db.dao.LikeDao
import com.zibro.ecommerce.data.db.entity.toDomainModel
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeDao: LikeDao
): LikeRepository {
    override fun getLikeProduct(): Flow<List<Product>> {
        return likeDao.getAll().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }
}