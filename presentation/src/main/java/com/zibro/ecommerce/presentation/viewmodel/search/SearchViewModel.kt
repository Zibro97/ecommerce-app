package com.zibro.ecommerce.presentation.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchKeyword
import com.zibro.ecommerce.domain.usecase.SearchUseCase
import com.zibro.ecommerce.presentation.delegate.ProductDelegate
import com.zibro.ecommerce.presentation.model.ProductVM
import com.zibro.ecommerce.presentation.ui.NavigationRouteName
import com.zibro.ecommerce.presentation.util.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel(), ProductDelegate {
    private val _searchResult = MutableStateFlow<List<ProductVM>>(listOf())
    val searchResult : StateFlow<List<ProductVM>> = _searchResult
    val searchKeywords = searchUseCase.getSearchKeywords()

    fun search(keyword : String) {
        viewModelScope.launch {
            searchUseCase.search(SearchKeyword(keyword = keyword)).collectLatest {
                _searchResult.emit(it.map(::convertToProductVM))
            }
        }
    }

    private fun convertToProductVM(product: Product) : ProductVM {
        return ProductVM(
            product,
            this
        )
    }

    override fun openProduct(navController: NavHostController, product: Product) {
        NavigationUtils.navigate(navController, NavigationRouteName.PRODUCT_DETAIL, product)
    }
}