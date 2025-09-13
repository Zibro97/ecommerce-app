package com.zibro.ecommerce.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zibro.ecommerce.data.db.entity.BasketProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    fun getAll() : Flow<List<BasketProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBasket(item : BasketProductEntity)

    @Query("SELECT * FROM basket WHERE productId=:id")
    suspend fun getBasketById(id : String) : BasketProductEntity?

    @Query("DELETE FROM basket WHERE productId = :id")
    suspend fun deleteBasket(id : String)

    @Query("DELETE FROM basket")
    suspend fun deleteAll()
}