package com.zibro.ecommerce.presentation.delegate

import com.zibro.ecommerce.domain.model.Product

interface ProductDelegate {
    fun openProduct(product : Product)
}