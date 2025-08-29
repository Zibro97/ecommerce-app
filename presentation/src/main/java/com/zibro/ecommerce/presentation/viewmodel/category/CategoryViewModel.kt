package com.zibro.ecommerce.presentation.viewmodel.category

import androidx.lifecycle.ViewModel
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase : CategoryUseCase
) : ViewModel() {
    private val _productsFlow = MutableStateFlow<List<Product>>(listOf())
    val productsFlow : StateFlow<List<Product>> = _productsFlow

    suspend fun updateCategory(category: Category) {
        useCase.getProductByCategory(category).collectLatest { products ->
            _productsFlow.emit(products)
        }
    }

    fun openProduct(product: Product) {

    }
}