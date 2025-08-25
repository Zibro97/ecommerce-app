package com.zibro.ecommerce.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.zibro.ecommerce.domain.model.BannerList
import com.zibro.ecommerce.presentation.R
import kotlinx.coroutines.delay

@Composable
fun BannerListCard(model : BannerList) {
    val pagerState = rememberPagerState()
    LaunchedEffect(key1 = pagerState) {
        autoScrollInfinity(pagerState)
    }
    HorizontalPager(count = model.imageList.size, state = pagerState) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                "description",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(2f)
            )
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("pageNumber : $it")
            }
        }
    }
}

private suspend fun autoScrollInfinity(pagerState : PagerState) {
    while (true) {
        delay(1000)
        val currentPage = pagerState.currentPage
        var nextPage = currentPage + 1
        if(currentPage +1 >= pagerState.pageCount) {
            nextPage = 0
        }
        pagerState.animateScrollToPage(nextPage)
    }
}