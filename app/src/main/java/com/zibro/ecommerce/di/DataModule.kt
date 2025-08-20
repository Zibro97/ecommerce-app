package com.zibro.ecommerce.di

import com.zibro.ecommerce.data.repository.MainRepositoryImpl
import com.zibro.ecommerce.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ) : MainRepository
}