package com.zibro.ecommerce.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.domain.model.SearchFilter
import com.zibro.ecommerce.domain.model.SearchKeyword
import com.zibro.ecommerce.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class SearchUseCaseTest {
    private lateinit var useCase : SearchUseCase

    @Mock
    private lateinit var searchRepository : SearchRepository

    @Mock
    private lateinit var topProduct : Product

    @Mock
    private lateinit var dressProduct : Product

    @Mock
    private lateinit var pantsProduct : Product

    private lateinit var searchResponse : List<Product>

    //mockito에서 테스트 후 자동으로 close 호출하도록
    private lateinit var autoClosable : AutoCloseable

    private val searchKeyword = SearchKeyword("1")

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        autoClosable = MockitoAnnotations.openMocks(this)

        `when`(topProduct.category).thenReturn(Category.Top)
        `when`(dressProduct.category).thenReturn(Category.Dress)
        `when`(pantsProduct.category).thenReturn(Category.Pants)

        `when`(topProduct.productName).thenReturn("상의1")
        `when`(dressProduct.productName).thenReturn("드레스1")
        `when`(pantsProduct.productName).thenReturn("바지1")

        searchResponse = listOf(topProduct, dressProduct, pantsProduct)

        useCase = SearchUseCase(searchRepository)
    }

    @After
    fun close() {
        Dispatchers.resetMain()
        autoClosable.close()
    }

    @Test
    fun search_call_test() {
        useCase.getSearchKeywords()

        verify(searchRepository).getSearchKeywords()
    }

    @Test
    fun top_filter_search_test() = runTest {
        `when`(searchRepository.search(searchKeyword)).thenReturn( flow {emit(searchResponse)})

        useCase.search(searchKeyword, listOf(SearchFilter.CategoryFilter(listOf(), Category.Top))).test {
            assertThat(awaitItem()).isEqualTo(listOf(topProduct))
            awaitComplete()
        }
    }

    @Test
    fun dress_filter_search_test() = runTest {
        `when`(searchRepository.search(searchKeyword)).thenReturn( flow {emit(searchResponse)})

        useCase.search(searchKeyword, listOf(SearchFilter.CategoryFilter(listOf(), Category.Dress))).test {
            assertThat(awaitItem()).isEqualTo(listOf(dressProduct))
            awaitComplete()
        }
    }

    @Test
    fun keyword_filter_search_test() = runTest {
        val keywordSearchKeyword = SearchKeyword("상의")
        `when`(searchRepository.search(keywordSearchKeyword)).thenReturn( flow {emit(searchResponse)})

        useCase.search(SearchKeyword("상의"), listOf()).test {
            assertThat(awaitItem()).isEqualTo(listOf(topProduct))
            awaitComplete()
        }
    }
}