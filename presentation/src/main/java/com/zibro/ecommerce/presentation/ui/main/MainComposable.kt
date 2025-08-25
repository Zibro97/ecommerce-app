package com.zibro.ecommerce.presentation.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zibro.ecommerce.domain.model.Banner
import com.zibro.ecommerce.domain.model.BannerList
import com.zibro.ecommerce.domain.model.ModelType
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.R
import com.zibro.ecommerce.presentation.component.BannerCard
import com.zibro.ecommerce.presentation.component.BannerListCard
import com.zibro.ecommerce.presentation.component.ProductCard
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(
        GridCells.Fixed(columnCount)
    ) {
        items(
            modelList.size,
            span = { index ->
                val item = modelList[index]
                val spanCount = getSpanCountByType(item.type, columnCount)
                GridItemSpan(spanCount)
            }
        ) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item)
                is Product -> ProductCard(product = item) {
                    // TODO: 상세 화면 개발 시 추가
                }
                is BannerList -> BannerListCard(model = item)
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when(type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST -> defaultColumnCount
        ModelType.RANKING -> defaultColumnCount
    }
}