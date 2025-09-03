package com.zibro.ecommerce.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zibro.ecommerce.domain.model.SearchKeyword

@Entity(tableName = "search")
data class SearchKeywordEntity(
    val keyword : String
) {
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
}

fun SearchKeywordEntity.toDomain() : SearchKeyword {
    return SearchKeyword(keyword = keyword)
}
