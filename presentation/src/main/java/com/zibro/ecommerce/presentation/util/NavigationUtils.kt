package com.zibro.ecommerce.presentation.util

import androidx.navigation.NavHostController
import com.zibro.ecommerce.presentation.ui.BasketNav
import com.zibro.ecommerce.presentation.ui.CategoryNav
import com.zibro.ecommerce.presentation.ui.Destination
import com.zibro.ecommerce.presentation.ui.MainNav
import com.zibro.ecommerce.presentation.ui.NavigationRouteName
import com.zibro.ecommerce.presentation.ui.ProductDetailNav
import com.zibro.ecommerce.presentation.ui.PurchaseHistoryNav
import com.zibro.ecommerce.presentation.ui.SearchNav

object NavigationUtils {
    fun navigate(
        controller : NavHostController,
        routeName : String,
        backStackRouteName : String? = null,
        isLaunchSingleTop : Boolean = true,
        needToRestoreState : Boolean = true
    ) {
        controller.navigate(routeName) {
            if(backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true }
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun findDestination(routeName: String?) : Destination {
        return when(routeName) {
            NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
            NavigationRouteName.MAIN_LIKE -> MainNav.Like
            NavigationRouteName.MAIN_HOME -> MainNav.Home
            NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
            NavigationRouteName.SEARCH -> SearchNav
            NavigationRouteName.BASKET -> BasketNav
            NavigationRouteName.PURCHASE_HISTORY -> PurchaseHistoryNav

            ProductDetailNav.routeWithArgName() -> ProductDetailNav
            CategoryNav.routeWithArgName() -> CategoryNav
            else -> MainNav.Home
        }
    }
}