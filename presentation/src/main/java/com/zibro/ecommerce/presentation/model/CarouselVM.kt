package com.zibro.ecommerce.presentation.model

import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Carousel
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.delegate.ProductDelegate

class CarouselVM(
    model: Carousel,
    private val productDelegate : ProductDelegate
) : PresentationVM<Carousel>(model){

    fun openCarouselProduct(navController: NavHostController, product : Product) {
        productDelegate.openProduct(navController,product)
        sendCarouselLog()
    }

    fun sendCarouselLog() {

    }
}