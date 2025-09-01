package com.zibro.ecommerce.presentation.ui.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.ModelType
import com.zibro.ecommerce.presentation.component.BannerCard
import com.zibro.ecommerce.presentation.component.BannerListCard
import com.zibro.ecommerce.presentation.component.CarouselCard
import com.zibro.ecommerce.presentation.component.ProductCard
import com.zibro.ecommerce.presentation.component.RankingCard
import com.zibro.ecommerce.presentation.model.BannerListVM
import com.zibro.ecommerce.presentation.model.BannerVM
import com.zibro.ecommerce.presentation.model.CarouselVM
import com.zibro.ecommerce.presentation.model.ProductVM
import com.zibro.ecommerce.presentation.model.RankingVM
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

@Composable
fun MainHomeScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(
        GridCells.Fixed(columnCount)
    ) {
        items(
            modelList.size,
            span = { index ->
                val item = modelList[index]
                val spanCount = getSpanCountByType(item.model.type, columnCount)
                GridItemSpan(spanCount)
            }
        ) {
            when (val item = modelList[it]) {
                is BannerVM -> BannerCard(presentationVM = item)
                is ProductVM -> ProductCard(
                    navHostController = navController,
                    presentationVM = item
                )

                is BannerListVM -> BannerListCard(presentationVM = item)
                is CarouselVM -> CarouselCard(
                    navHostController = navController,
                    presentationVM = item
                )

                is RankingVM -> RankingCard(
                    navHostController = navController,
                    presentationVM = item
                )
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST, ModelType.CAROUSEL -> defaultColumnCount
        ModelType.RANKING -> defaultColumnCount
    }
}