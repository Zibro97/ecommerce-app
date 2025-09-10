package com.zibro.ecommerce.presentation.model

import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.presentation.delegate.ProductDelegate

class RankingVM(
    model : Ranking,
    private val productDelegate: ProductDelegate
) : PresentationVM<Ranking>(model){

    fun openRankingProduct(navController: NavHostController, product : Product) {
        productDelegate.openProduct(navController,product)
        sendRankingLog()
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product)
    }

    private fun sendRankingLog() {

    }
}