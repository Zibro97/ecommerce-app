package com.zibro.ecommerce.presentation.delegate

import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Product

interface ProductDelegate {
    fun openProduct(navController: NavHostController, product : Product)
}