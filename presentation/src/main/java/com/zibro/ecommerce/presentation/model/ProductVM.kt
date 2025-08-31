package com.zibro.ecommerce.presentation.model

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.presentation.delegate.ProductDelegate

class ProductVM(
    model : Product,
    productDelegate: ProductDelegate
) : PresentationVM<Product>(model), ProductDelegate by productDelegate {

    fun openRankingProduct(product : Product) {
        productDelegate.openProduct(product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }
}