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


sealed class MainNav(
    override val route: String,
    val icon: ImageVector,
    override val title : String
) : Destination {
    data object Home : MainNav(route = MAIN_HOME, title = NavigationTitle.MAIN_HOME, icon = Icons.Filled.Home)
    data object Category : MainNav(route = MAIN_CATEGORY, title = NavigationTitle.MAIN_CATEGORY, icon = Icons.Filled.Star)
    data object MyPage : MainNav(route = MAIN_MY_PAGE, title = NavigationTitle.MAIN_MY_PAGE, icon = Icons.Filled.Person)
    data object Like : MainNav(route = MAIN_LIKE, title = NavigationTitle.MAIN_LIKE, icon = Icons.Filled.Favorite)

    override val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
    )

    companion object {
        fun isMainRoute(route: String?): Boolean {
            return when(route) {
                MAIN_HOME, MAIN_CATEGORY, MAIN_MY_PAGE, MAIN_LIKE -> true
                else -> false
            }
        }
    }
}

object SearchNav : Destination {
    override val route: String
        get() = NavigationRouteName.SEARCH
    override val title: String
        get() = NavigationRouteName.SEARCH
    override val deepLinks: List<NavDeepLink>
        get() = listOf(
            navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
        )

}

object BasketNav : Destination {
    override val route: String
        get() = NavigationRouteName.BASKET
    override val title: String
        get() = NavigationRouteName.BASKET
    override val deepLinks: List<NavDeepLink>
        get() = listOf(
            navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
        )

}

object PurchaseHistoryNav : Destination {
    override val route: String
        get() = NavigationRouteName.PURCHASE_HISTORY
    override val title: String
        get() = NavigationRouteName.PURCHASE_HISTORY
    override val deepLinks: List<NavDeepLink>
        get() = listOf(
            navDeepLink { uriPattern = "$DEEP_LINK_SCHEME$route" }
        )

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

object ProductDetailNav : DestinationArgs<String> {
    override val argName: String = "productId"
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(argName) { type = NavType.StringType }
    )

    override fun navigateWithArg(item: String): String {
        val arg = GsonUtils.toJson(item)
        return "$route/{$arg}"
    }

    override fun findArgument(navBackStackEntry: NavBackStackEntry): String? {
        val categoryString = navBackStackEntry.arguments?.getString(argName)
        return GsonUtils.fromJson(categoryString)
    }

    override val title: String = NavigationTitle.PRODUCT_DETAIL
    override val route: String = NavigationRouteName.PRODUCT_DETAIL
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
    const val PURCHASE_HISTORY = "purchase_history"
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
    const val PURCHASE_HISTORY = "결제내역"
}