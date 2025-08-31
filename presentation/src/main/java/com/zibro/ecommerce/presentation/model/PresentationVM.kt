package com.zibro.ecommerce.presentation.model

import com.zibro.ecommerce.domain.model.BaseModel

sealed class PresentationVM<T : BaseModel>(val model: T) {

}