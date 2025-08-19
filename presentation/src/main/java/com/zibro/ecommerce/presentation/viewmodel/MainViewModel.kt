package com.zibro.ecommerce.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    fun openSearchForm() {
        Log.d("zibro", "openSearchForm: ")
    }
}