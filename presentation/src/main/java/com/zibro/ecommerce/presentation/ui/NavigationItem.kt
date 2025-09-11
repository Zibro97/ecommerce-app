package com.zibro.ecommerce.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.CATEGORY
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_CATEGORY
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_HOME
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_LIKE
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_MY_PAGE
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.PRODUCT_DETAIL
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.SEARCH

sealed class NavigationItem(open val route: String) {
    sealed class MainNav(
        override val route: String,
        val name: String,
        val icon: ImageVector
    ) : NavigationItem(route) {
        data object Home : MainNav(MAIN_HOME, MAIN_HOME, Icons.Filled.Home)
        data object Category : MainNav(MAIN_CATEGORY, MAIN_CATEGORY, Icons.Filled.Star)
        data object MyPage : MainNav(MAIN_MY_PAGE, MAIN_MY_PAGE, Icons.Filled.Person)
        data object Like : MainNav(MAIN_LIKE, MAIN_LIKE, Icons.Filled.Favorite)

        companion object {
            fun isMainRoute(route: String?): Boolean {
                return when(route) {
                    MAIN_HOME, MAIN_CATEGORY, MAIN_MY_PAGE, MAIN_LIKE -> true
                    else -> false
                }
            }
        }
    }

    sealed class CategoryNav(
        val category: Category
    ) : NavigationItem(CATEGORY) {

    }

    sealed class ProductDetailNav(
        val product : Product
    ) : NavigationItem(PRODUCT_DETAIL)

    object SearchNav : NavigationItem(SEARCH)
}

object NavigationRouteName {
    const val MAIN_HOME = "main_home"
    const val MAIN_CATEGORY = "main_category"
    const val MAIN_MY_PAGE = "main_my_page"
    const val MAIN_LIKE = "main_like"
    const val CATEGORY = "category"
    const val PRODUCT_DETAIL = "product_detail"
    const val SEARCH = "search"
}