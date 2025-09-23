package com.zibro.ecommerce.data.repository

import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.zibro.ecommerce.data.db.AppDatabase
import com.zibro.ecommerce.data.db.dao.BasketDao
import com.zibro.ecommerce.data.db.dao.PurchaseHistoryDao
import com.zibro.ecommerce.data.db.entity.BasketProductEntity
import com.zibro.ecommerce.domain.model.BasketProduct
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Price
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SalesStatus
import com.zibro.ecommerce.domain.model.Shop
import com.zibro.ecommerce.domain.repository.BasketRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(
    application = HiltTestApplication::class,
    sdk = [Build.VERSION_CODES.P]
)
@RunWith(RobolectricTestRunner::class)
class BasketRepositoryTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database : AppDatabase

    lateinit var basketDao : BasketDao
    lateinit var purchaseHistoryDao: PurchaseHistoryDao
    lateinit var basketRepository: BasketRepository

    private val price = Price(10000, 10000, SalesStatus.ON_SALE)
    private val shop = Shop("0", "0", "")
    private val category = Category.Top

    private val basketProductEntity = BasketProductEntity(
        productId = "",
        productName = "",
        imageUrl = "",
        price = price,
        category = category,
        shop = shop,
        isNew = false,
        isFreeShipping = false,
        isLike = false,
        count = 1
    )

    private val basketProduct = BasketProduct(
        product = Product(
            productId = "",
            productName = "",
            imageUrl = "",
            price = price,
            category = category,
            shop = shop,
            isNew = false,
            isLike = false,
            isFreeShipping = false,
        ),
        count = 1
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        hiltRule.inject()
        basketDao = database.basketDao()
        purchaseHistoryDao = database.purchaseHistoryDao()
        basketRepository = BasketRepositoryImpl(basketDao, purchaseHistoryDao)
    }

    @After
    fun close() {
        Dispatchers.resetMain()
        database.close()
    }

    @Test
    fun `결제 테스트`() = runTest {
        basketDao.insertBasket(basketProductEntity)

        basketRepository.checkBasket(listOf(basketProduct))

        assertThat(purchaseHistoryDao.getBasketById("1")).isNotNull()
        assertThat(basketDao.getBasketById(basketProduct.product.productId)).isNull()
    }
}