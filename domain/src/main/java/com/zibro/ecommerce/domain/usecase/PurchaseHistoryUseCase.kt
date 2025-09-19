package com.zibro.ecommerce.domain.usecase

import com.zibro.ecommerce.domain.model.PurchaseHistory
import com.zibro.ecommerce.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PurchaseHistoryUseCase @Inject constructor(
  private val repository: PurchaseHistoryRepository
) {
    fun getPurchaseHistory() : Flow<List<PurchaseHistory>> = repository.getPurchaseHistory()
}