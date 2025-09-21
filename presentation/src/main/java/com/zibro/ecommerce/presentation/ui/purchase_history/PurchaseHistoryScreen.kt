package com.zibro.ecommerce.presentation.ui.purchase_history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.PurchaseHistory
import com.zibro.ecommerce.presentation.R
import com.zibro.ecommerce.presentation.component.Price
import com.zibro.ecommerce.presentation.util.NumberUtils
import com.zibro.ecommerce.presentation.viewmodel.purchase_history.PurchaseViewModel

@Composable
fun PurchaseHistoryScreen(
    viewModel: PurchaseViewModel = hiltViewModel()
) {
    val purchaseHistory by viewModel.purchaseHistory.collectAsState(listOf())

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
    ) {
        purchaseHistory.forEach { it ->
            purchaseHistoryCard(it)
        }
    }
}

fun LazyListScope.purchaseHistoryCard(
    purchaseHistory: PurchaseHistory
) {
    item {
        Text(
            fontSize = 16.sp,
            text = "결제 시기 : ${purchaseHistory.purchaseDate}"
        )
    }

    items(purchaseHistory.basketList.size) { index ->
        val currentItem = purchaseHistory.basketList[index]

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.product_image),
                contentDescription = "purchaseItem",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,0.dp)
                    .weight(1f)
            ) {
                Text(
                    fontSize = 14.sp,
                    text = "${currentItem.product.shop.shopName} - ${currentItem.product.productName}",
                )
                Price(product = currentItem.product)
            }
            Text(
                text = "${currentItem.count} 개",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp,0.dp,0.dp,0.dp)
            )
        }
    }

    item {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp,0.dp,0.dp,20.dp),
            fontSize = 16.sp,
            text = "${getTotalPrice(purchaseHistory.basketList)} 결제 완료"
        )
    }
}

private fun getTotalPrice(list: List<BasketProduct>): String {
    val totalPrice = list.sumOf { it.product.price.finalPrice * it.count }

    return NumberUtils.numberFormatPrice(totalPrice)
}