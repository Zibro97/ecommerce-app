package com.zibro.ecommerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zibro.ecommerce.data.db.entity.PurchaseProductEntity

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchase")
    suspend fun getAll() : List<PurchaseProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLike(item : PurchaseProductEntity)

    @Query("DELETE FROM purchase WHERE productId = :id")
    suspend fun deleteLike(id : String)
}