package com.zibro.ecommerce.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

@Composable
fun CategoryScreen(viewModel : MainViewModel) {
    val categories by viewModel.categoryList.collectAsState(initial = listOf())

    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(
            categories.size,
            span = {
                GridItemSpan(1)
            }
        ) {
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
                    .shadow(10.dp)
            ) {
                Text(
                    text = categories[it].categoryName,
                    modifier = Modifier.fillMaxSize()
                        .padding(10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}