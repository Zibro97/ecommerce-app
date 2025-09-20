package com.zibro.ecommerce.di

import com.zibro.ecommerce.data.repository.AccountRepositoryImpl
import com.zibro.ecommerce.data.repository.BasketRepositoryImpl
import com.zibro.ecommerce.data.repository.CategoryRepositoryImpl
import com.zibro.ecommerce.data.repository.LikeRepositoryImpl
import com.zibro.ecommerce.data.repository.MainRepositoryImpl
import com.zibro.ecommerce.data.repository.ProductDetailRepositoryImpl
import com.zibro.ecommerce.data.repository.PurchaseHistoryRepositoryImpl
import com.zibro.ecommerce.data.repository.SearchRepositoryImpl
import com.zibro.ecommerce.domain.repository.AccountRepository
import com.zibro.ecommerce.domain.repository.BasketRepository
import com.zibro.ecommerce.domain.repository.CategoryRepository
import com.zibro.ecommerce.domain.repository.LikeRepository
import com.zibro.ecommerce.domain.repository.MainRepository
import com.zibro.ecommerce.domain.repository.ProductDetailRepository
import com.zibro.ecommerce.domain.repository.PurchaseHistoryRepository
import com.zibro.ecommerce.domain.repository.SearchRepository
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

    @Binds
    @Singleton
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ) : CategoryRepository

    @Binds
    @Singleton
    fun bindProductDetailRepository(
        productDetailRepositoryImpl: ProductDetailRepositoryImpl
    ) : ProductDetailRepository

    @Binds
    @Singleton
    fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ) : SearchRepository

    @Binds
    @Singleton
    fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl
    ) : AccountRepository

    @Binds
    @Singleton
    fun bindLikeRepository(
        likeRepositoryImpl: LikeRepositoryImpl
    ) : LikeRepository

    @Binds
    @Singleton
    fun bindBasketRepository(
        basketRepositoryImpl: BasketRepositoryImpl
    ) : BasketRepository

    @Binds
    @Singleton
    fun bindPurchaseHistoryRepository(
        purchaseHistoryRepositoryImpl: PurchaseHistoryRepositoryImpl
    ) : PurchaseHistoryRepository
}