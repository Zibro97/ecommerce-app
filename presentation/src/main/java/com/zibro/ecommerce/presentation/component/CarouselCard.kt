package com.zibro.ecommerce.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.R
import com.zibro.ecommerce.presentation.model.CarouselVM
import com.zibro.ecommerce.presentation.model.PresentationVM

@Composable
fun CarouselCard(
    navHostController: NavHostController,
    presentationVM : CarouselVM
) {
    val scrollState = rememberLazyListState()
    Column {
        Text(
            text = presentationVM.model.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(10.dp)
        )

        LazyRow(
            state = scrollState,
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(presentationVM.model.productList.size) { productIndex ->
                CarouselProductCard(
                    product = presentationVM.model.productList[productIndex],
                    presentationVM
                ) {
                    presentationVM.openCarouselProduct(navHostController,it)
                }
            }
        }
    }
}

@Composable
private fun CarouselProductCard(
    product : Product,
    presentationVM : CarouselVM,
    onClick : (Product) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp),
        onClick = { onClick(product) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { presentationVM.likeProduct(product) }
            ) {
                Icon(
                    if (product.isLike) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    "FavoriteIcon"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.product_image),
                    "description",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Text(
                    fontSize = 14.sp,
                    text = product.productName
                )
                Price(product)
            }
        }
    }
}