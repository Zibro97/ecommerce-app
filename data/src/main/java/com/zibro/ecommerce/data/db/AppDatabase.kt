package com.zibro.ecommerce.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zibro.ecommerce.data.db.dao.BasketDao
import com.zibro.ecommerce.data.db.dao.LikeDao
import com.zibro.ecommerce.data.db.dao.PurchaseDao
import com.zibro.ecommerce.data.db.dao.PurchaseHistoryDao
import com.zibro.ecommerce.data.db.dao.SearchDao
import com.zibro.ecommerce.data.db.entity.BasketProductEntity
import com.zibro.ecommerce.data.db.entity.LikeProductEntity
import com.zibro.ecommerce.data.db.entity.PurchaseHistoryEntity
import com.zibro.ecommerce.data.db.entity.PurchaseProductEntity
import com.zibro.ecommerce.data.db.entity.SearchKeywordEntity

@Database(
    entities = [
        LikeProductEntity::class,
        BasketProductEntity::class,
        PurchaseProductEntity::class,
        SearchKeywordEntity::class,
        PurchaseHistoryEntity::class
    ],
    version = 3,
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "AppDatabase.db"
    }

    abstract fun likeDao() : LikeDao
    abstract fun basketDao() : BasketDao
    abstract fun purchaseDao() : PurchaseDao
    abstract fun searchDao() : SearchDao

    abstract fun purchaseHistoryDao() : PurchaseHistoryDao
}