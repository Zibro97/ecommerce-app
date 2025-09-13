package com.zibro.ecommerce.presentation.viewmodel.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.usecase.BasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketUseCase: BasketUseCase
) : ViewModel() {
    val basketProduct = basketUseCase.getBasketProducts()

    fun removeBasketProduct(product : Product) = viewModelScope.launch {
        basketUseCase.removeBasketProducts(product)
    }
}