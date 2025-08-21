package com.zibro.ecommerce.presentation.ui.main

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.zibro.ecommerce.presentation.common.ProductCard
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val productList by viewModel.productList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(
        GridCells.Fixed(columnCount)
    ) {
        items(productList.size) {
            Log.d("zibro", "MainInsideScreen: $productList")
            ProductCard(product = productList[it]) {
                // TODO: 상세 화면 개발 시 추가
            }
        }
    }
}