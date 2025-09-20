package com.zibro.ecommerce.presentation.viewmodel.purchase_history

import androidx.lifecycle.ViewModel
import com.zibro.ecommerce.domain.usecase.PurchaseHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val useCase : PurchaseHistoryUseCase
) : ViewModel() {
    val purchaseHistory = useCase.getPurchaseHistory()
}