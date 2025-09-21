package com.zibro.ecommerce.presentation.viewmodel.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.usecase.BasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketUseCase: BasketUseCase
) : ViewModel() {
    val basketProduct = basketUseCase.getBasketProducts()

    private val _eventFlow = MutableSharedFlow<BasketEvent>()
    val eventFlow: SharedFlow<BasketEvent> = _eventFlow

    fun dispatch(action: BasketAction) {
        when (action) {
            is BasketAction.RemoveProduct -> {
                removeBasketProduct(action.product)
            }

            is BasketAction.CheckOutBasket -> {
                checkOutBasket(action.products)
            }
        }
    }

    private fun removeBasketProduct(product: Product) = viewModelScope.launch {
        basketUseCase.removeBasketProducts(product)
    }

    private fun checkOutBasket(products: List<BasketProduct>) = viewModelScope.launch {
        basketUseCase.checkOutBasket(products)
    }
}

sealed class BasketEvent {
    object ShowSnackBar : BasketEvent()
}

sealed class BasketAction {
    data class RemoveProduct(val product: Product) : BasketAction()
    data class CheckOutBasket(val products: List<BasketProduct>) : BasketAction()
}