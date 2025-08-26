package com.zibro.ecommerce.domain.model

data class Ranking(
    val rankingId : String,
    val title : String,
    val productList : List<Product>,
    override val type: ModelType,
) : BaseModel()