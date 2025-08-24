package com.zibro.ecommerce.domain.model

data class Banner(
    val bannerId : String,
    val imageUrl : String,
    override val type: ModelType = ModelType.BANNER
) : BaseModel()