package com.zibro.ecommerce.presentation.model

import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.delegate.ProductDelegate

class ProductVM(
    model : Product,
    private val productDelegate: ProductDelegate
) : PresentationVM<Product>(model), ProductDelegate by productDelegate {

    fun openRankingProduct(navHostController: NavHostController,product : Product) {
        productDelegate.openProduct(navHostController,product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }
}