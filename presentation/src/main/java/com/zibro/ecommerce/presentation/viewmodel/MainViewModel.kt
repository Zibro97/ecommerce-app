package com.zibro.ecommerce.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Banner
import com.zibro.ecommerce.domain.model.BannerList
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.domain.usecase.CategoryUseCase
import com.zibro.ecommerce.domain.usecase.MainUseCase
import com.zibro.ecommerce.presentation.ui.NavigationRouteName
import com.zibro.ecommerce.presentation.util.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase,
) : ViewModel() {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount : StateFlow<Int> = _columnCount

    val modelList = mainUseCase()
    val categoryList = categoryUseCase.getCategory()

    fun openSearchForm() {

    }

    fun updateColumnCount(count : Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    fun openProduct(product: Product) {

    }

    fun openCarouselProduct(product: Product) {

    }

    fun openBanner(banner: Banner) {

    }

    fun openBannerList(bannerList: BannerList) {

    }

    fun openRanking(ranking: Ranking) {

    }

    fun openCategory(navController: NavHostController, category: Category) {
        NavigationUtils.navigate(
            navController,
            NavigationRouteName.CATEGORY,
            category
        )
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }
}