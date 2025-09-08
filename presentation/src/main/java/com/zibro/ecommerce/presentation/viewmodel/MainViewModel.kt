package com.zibro.ecommerce.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.AccountInfo
import com.zibro.ecommerce.domain.model.Banner
import com.zibro.ecommerce.domain.model.BannerList
import com.zibro.ecommerce.domain.model.BaseModel
import com.zibro.ecommerce.domain.model.Carousel
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.ModelType
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.Ranking
import com.zibro.ecommerce.domain.usecase.AccountUseCase
import com.zibro.ecommerce.domain.usecase.CategoryUseCase
import com.zibro.ecommerce.domain.usecase.MainUseCase
import com.zibro.ecommerce.presentation.delegate.BannerDelegate
import com.zibro.ecommerce.presentation.delegate.CategoryDelegate
import com.zibro.ecommerce.presentation.delegate.ProductDelegate
import com.zibro.ecommerce.presentation.model.BannerListVM
import com.zibro.ecommerce.presentation.model.BannerVM
import com.zibro.ecommerce.presentation.model.CarouselVM
import com.zibro.ecommerce.presentation.model.PresentationVM
import com.zibro.ecommerce.presentation.model.ProductVM
import com.zibro.ecommerce.presentation.model.RankingVM
import com.zibro.ecommerce.presentation.ui.NavigationRouteName
import com.zibro.ecommerce.presentation.util.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase,
    private val accountUseCase: AccountUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount : StateFlow<Int> = _columnCount

    val modelList = mainUseCase().map(::convertToPresentationVM)
    val categoryList = categoryUseCase.getCategory()
    val accountInfo = accountUseCase.getAccountInfo()

    fun openSearchForm(
        navController: NavHostController
    ) {
        NavigationUtils.navigate(navController, NavigationRouteName.SEARCH)
    }

    fun signInGoogle(accountInfo : AccountInfo) {
        viewModelScope.launch {
            accountUseCase.signInGoogle(accountInfo)
        }
    }

    fun signOutGoogle() {
        viewModelScope.launch {
            accountUseCase.signOutGoogle()
        }
    }

    fun updateColumnCount(count : Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun openProduct(
        navController: NavHostController,
        product: Product
    ) {
        NavigationUtils.navigate(navController, NavigationRouteName.PRODUCT_DETAIL, product)
    }

    fun openCarouselProduct(product: Product) {

    }


    fun openBannerList(bannerList: BannerList) {

    }

    fun openRanking(ranking: Ranking) {

    }

    override fun openCategory(navController: NavHostController, category: Category) {
        NavigationUtils.navigate(
            navController,
            NavigationRouteName.CATEGORY,
            category
        )
    }

    override fun openBanner(bannerId: String) {
        TODO("Not yet implemented")
    }

    private fun convertToPresentationVM(list : List<BaseModel>) : List<PresentationVM<out BaseModel>> {
        return list.map { model ->
            when(model) {
                is Product -> ProductVM(model, this)
                is Banner -> BannerVM(model, this)
                is BannerList -> BannerListVM(model, this)
                is Ranking -> RankingVM(model, this)
                is Carousel -> CarouselVM(model,this)
            }
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }
}