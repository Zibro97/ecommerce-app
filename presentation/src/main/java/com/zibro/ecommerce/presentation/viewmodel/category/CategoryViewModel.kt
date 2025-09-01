package com.zibro.ecommerce.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.usecase.CategoryUseCase
import com.zibro.ecommerce.presentation.delegate.ProductDelegate
import com.zibro.ecommerce.presentation.model.ProductVM
import com.zibro.ecommerce.presentation.ui.NavigationRouteName
import com.zibro.ecommerce.presentation.util.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase : CategoryUseCase
) : ViewModel(), ProductDelegate {
    private val _productsFlow = MutableStateFlow<List<ProductVM>>(listOf())
    val productsFlow : StateFlow<List<ProductVM>> = _productsFlow

    suspend fun updateCategory(category: Category) {
        useCase.getProductByCategory(category).collectLatest { products ->
            _productsFlow.emit(convertToPresentationVM(products))
        }
    }

    override fun openProduct(navController: NavHostController,product: Product) {
        NavigationUtils.navigate(navController, NavigationRouteName.PRODUCT_DETAIL, product)
    }

    private fun convertToPresentationVM(list:List<Product>) : List<ProductVM> {
        return list.map {
            ProductVM(it, this)
        }
    }
}