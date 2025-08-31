package com.zibro.ecommerce.presentation.model

import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.presentation.delegate.ProductDelegate

class RankingVM(
    model : Ranking,
    private val productDelegate: ProductDelegate
) : PresentationVM<Ranking>(model){

    fun openRankingProduct(product : Product) {
        productDelegate.openProduct(product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }
}