package com.zibro.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.zibro.ecommerce.data.db.AppDatabase
import com.zibro.ecommerce.data.db.dao.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(
        database: AppDatabase
    ) : SearchDao {
        return database.searchDao()
    }

}