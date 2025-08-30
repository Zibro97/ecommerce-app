package com.zibro.ecommerce.presentation.delegate

import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Category

interface CategoryDelegate {
    fun openCategory(
        navController: NavHostController,
        category: Category
    )
}