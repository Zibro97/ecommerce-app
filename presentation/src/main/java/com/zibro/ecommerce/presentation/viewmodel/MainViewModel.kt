package com.zibro.ecommerce.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.zibro.ecommerce.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    useCase: MainUseCase
) : ViewModel() {
    val productList = useCase()

    fun openSearchForm() {
        productList
    }
}