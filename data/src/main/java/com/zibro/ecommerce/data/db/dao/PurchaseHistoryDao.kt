package com.zibro.ecommerce.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.zibro.ecommerce.data.db.converter.PurchaseHistoryConverter
import com.zibro.ecommerce.data.db.entity.PurchaseHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseHistoryDao {

    @Query("SELECT * FROM history")
    fun getAll() : Flow<List<PurchaseHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : PurchaseHistoryEntity)

    @Query("SELECT * FROM history WHERE id=:id")
    suspend fun getBasketById(id : String) : PurchaseHistoryEntity?

    @Query("DELETE FROM history WHERE id = :id")
    suspend fun deleteBasket(id : String)

    @Query("DELETE FROM history")
    suspend fun deleteAll()
}