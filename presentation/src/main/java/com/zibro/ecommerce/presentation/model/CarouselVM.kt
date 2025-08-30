package com.zibro.ecommerce.presentation.model

import com.zibro.ecommerce.domain.model.Carousel
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.delegate.ProductDelegate

class CarouselVM(
    model: Carousel,
    private val productDelegate : ProductDelegate
) : PresentationVM(model){

    fun openCarouselProduct(product : Product) {
        productDelegate.openProduct(product)
        sendCarouselLog()
    }

    fun sendCarouselLog() {

    }
}