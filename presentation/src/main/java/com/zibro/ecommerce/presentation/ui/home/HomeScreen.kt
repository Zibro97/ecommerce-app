package com.zibro.ecommerce.presentation.ui.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.zibro.ecommerce.domain.model.Banner
import com.zibro.ecommerce.domain.model.BannerList
import com.zibro.ecommerce.domain.model.Carousel
import com.zibro.ecommerce.domain.model.ModelType
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.presentation.component.BannerCard
import com.zibro.ecommerce.presentation.component.BannerListCard
import com.zibro.ecommerce.presentation.component.CarouselCard
import com.zibro.ecommerce.presentation.component.ProductCard
import com.zibro.ecommerce.presentation.component.RankingCard
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
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
                is Banner -> BannerCard(model = item) { model ->
                    viewModel.openBanner(model)
                }
                is Product -> ProductCard(product = item) { model ->
                    viewModel.openProduct(model)
                }
                is BannerList -> BannerListCard(model = item) { model ->
                    viewModel.openBannerList(model)
                }
                is Carousel -> CarouselCard(model = item) { model ->
                    viewModel.openCarouselProduct(model)
                }
                is Ranking -> RankingCard(model = item) { model ->
                    viewModel.openRanking(model)
                }
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when(type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST, ModelType.CAROUSEL -> defaultColumnCount
        ModelType.RANKING -> defaultColumnCount
    }
}