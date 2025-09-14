package com.zibro.ecommerce.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.domain.model.Product
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.BASKET
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.CATEGORY
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.DEEP_LINK_SCHEME
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_CATEGORY
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_HOME
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_LIKE
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.MAIN_MY_PAGE
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.PRODUCT_DETAIL
import com.zibro.ecommerce.presentation.ui.NavigationRouteName.SEARCH
import com.zibro.ecommerce.presentation.util.GsonUtils

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

    sealed class ProductDetailNav(
        val product : Product
    ) : NavigationItem(PRODUCT_DETAIL)

    object SearchNav : NavigationItem(SEARCH)

    object BasketNav : NavigationItem(BASKET)
}

object CategoryNav : DestinationArgs<Category> {
    override val argName: String = "category"
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type = NavType.StringType }
    )

    override fun navigateWithArg(item: Category): String {
        val arg = GsonUtils.toJson(item)
        return "$route/{$arg}"
    }

    override fun findArgument(navBackStackEntry: NavBackStackEntry): Category? {
        val categoryString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson(categoryString)
    }

    override val title: String = NavigationTitle.CATEGORY
    override val route: String = NavigationRouteName.CATEGORY
    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route/{$argName}" }
    )

}

interface Destination {
    val route: String
    val title : String
    val deepLinks : List<NavDeepLink>
}

interface DestinationArgs<T> : Destination {
    val argName : String
    val arguments : List<NamedNavArgument>

    fun routeWithArgName() = "$route/{$argName}"

    fun navigateWithArg(item : T) : String

    fun findArgument(navBackStackEntry: NavBackStackEntry) : T?
}

object NavigationRouteName {
    const val DEEP_LINK_SCHEME = "zibro://"
    const val MAIN_HOME = "main_home"
    const val MAIN_CATEGORY = "main_category"
    const val MAIN_MY_PAGE = "main_my_page"
    const val MAIN_LIKE = "main_like"
    const val CATEGORY = "category"
    const val PRODUCT_DETAIL = "product_detail"
    const val SEARCH = "search"

    const val BASKET = "basket"
}

object NavigationTitle {
    const val MAIN_HOME = "홈"
    const val MAIN_CATEGORY = "카테고리"
    const val MAIN_MY_PAGE = "마이페이지"
    const val MAIN_LIKE = "좋아요"
    const val CATEGORY = "카테고리별 보기"
    const val PRODUCT_DETAIL = "상품 상세페이지"
    const val SEARCH = "검색"
    const val BASKET = "장바구니"
}