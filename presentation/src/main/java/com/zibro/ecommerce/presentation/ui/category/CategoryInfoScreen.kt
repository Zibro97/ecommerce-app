package com.zibro.ecommerce.presentation.ui.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.presentation.component.ProductCard
import com.zibro.ecommerce.presentation.viewmodel.category.CategoryViewModel

@Composable
fun CategoryInfoScreen(
    category: Category,
    navHostController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val products by viewModel.productsFlow.collectAsState()

    LaunchedEffect(key1 = category) {
        viewModel.updateCategory(category)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(products.size) { idx ->
            ProductCard(navHostController = navHostController,presentationVM = products[idx])
        }
    }
}