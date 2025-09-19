package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.PurchaseHistory
import kotlinx.coroutines.flow.Flow

interface PurchaseHistoryRepository {
    fun getPurchaseHistory() : Flow<List<PurchaseHistory>>
}