package com.zibro.ecommerce.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.SearchKeyword
import com.zibro.ecommerce.presentation.component.ProductCard
import com.zibro.ecommerce.presentation.viewmodel.search.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchResult by viewModel.searchResult.collectAsState()
    val searchKeyword by viewModel.searchKeywords.collectAsState(listOf())
    var keyword by remember { mutableStateOf("") }

    // 검색바
    // 검색 결과 or 최근 검색어
    Column {
        SearchBox(
            keyword = keyword,
            onValueChange = { keyword = it },
            searchAction = {
                viewModel.search(keyword)
            })
        if(searchResult.isEmpty()) {
            Text(
                modifier = Modifier.padding(6.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "최근 검색어"
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
            ) {
                items (searchKeyword.size) { index ->
                    val currentKeyword = searchKeyword.reversed()[index].keyword
                    Button(
                        onClick = {
                            keyword = currentKeyword
                            viewModel.search(keyword)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified)
                    ) {
                        Text(
                            text = currentKeyword,
                            fontSize = 18.sp,
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(searchResult.size) { index ->
                    ProductCard(
                        navHostController = navController,
                        presentationVM = searchResult[index]
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBox(
    keyword: String,
    onValueChange : (String) -> Unit,
    searchAction : () -> Unit
) {
    Row(
        Modifier.fillMaxWidth()
    ) {
       TextField(
           value = keyword,
           onValueChange = onValueChange,
           placeholder = {
               Text("검색어를 입력해주세요.")
           },
           shape = RoundedCornerShape(size = 8.dp),
           keyboardOptions = KeyboardOptions(
               imeAction = ImeAction.Search,
               keyboardType = KeyboardType.Text,
               capitalization = KeyboardCapitalization.Words,
               autoCorrect = true
           ),
           keyboardActions = KeyboardActions(
               onSearch = {
                   searchAction()
               }
           ),
           modifier = Modifier.fillMaxWidth()
               .padding(10.dp),
           maxLines = 1,
           singleLine = true, // 특정 기기에서 maxLines가 안먹는 케이스가 있다고 함
           leadingIcon = { Icon(Icons.Filled.Search, "SearchIcon") }
       )
    }

}