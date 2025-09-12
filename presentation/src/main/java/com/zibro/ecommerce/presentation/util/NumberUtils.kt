package com.zibro.ecommerce.presentation.util

import java.text.NumberFormat

object NumberUtils {
    fun numberFormatPrice(price : Int?) : String{
        return NumberFormat.getNumberInstance().format(price ?: 0)
    }
}