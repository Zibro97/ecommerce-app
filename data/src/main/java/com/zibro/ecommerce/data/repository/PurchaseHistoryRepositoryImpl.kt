package com.zibro.ecommerce.data.repository

import com.zibro.ecommerce.data.db.dao.PurchaseHistoryDao
import com.zibro.ecommerce.data.db.entity.toDomainModel
import com.zibro.ecommerce.domain.model.PurchaseHistory
import com.zibro.ecommerce.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseHistoryRepositoryImpl @Inject constructor(
    private val purchaseHistoryDao: PurchaseHistoryDao
) : PurchaseHistoryRepository {
    override fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
        return purchaseHistoryDao.getAll().map { list ->
            list.map {
                it.toDomainModel()
            }
        }
    }
}