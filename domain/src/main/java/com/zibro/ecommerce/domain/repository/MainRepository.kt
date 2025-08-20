package com.zibro.ecommerce.domain.repository

import com.zibro.ecommerce.domain.model.Product

interface MainRepository {
    fun getProductList() : List<Product>
}