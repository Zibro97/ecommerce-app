package com.zibro.ecommerce.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zibro.ecommerce.domain.model.Banner
import com.zibro.ecommerce.presentation.R
import com.zibro.ecommerce.presentation.model.BannerVM
import com.zibro.ecommerce.presentation.model.PresentationVM

@Composable
fun BannerCard(presentationVM : BannerVM) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(20.dp),
        onClick = { presentationVM.openBanner(presentationVM.model.bannerId) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.product_image),
            "description",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(2f)
        )

    }
}