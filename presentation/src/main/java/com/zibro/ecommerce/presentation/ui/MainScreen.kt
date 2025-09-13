package com.zibro.ecommerce.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.gson.Gson
import com.zibro.ecommerce.domain.model.Category
import com.zibro.ecommerce.presentation.ui.basket.BasketScreen
import com.zibro.ecommerce.presentation.ui.category.CategoryInfoScreen
import com.zibro.ecommerce.presentation.ui.home.LikeScreen
import com.zibro.ecommerce.presentation.ui.home.MainCategoryScreen
import com.zibro.ecommerce.presentation.ui.home.MainHomeScreen
import com.zibro.ecommerce.presentation.ui.home.MyPageScreen
import com.zibro.ecommerce.presentation.ui.product_detail.ProductDetailScreen
import com.zibro.ecommerce.presentation.ui.search.SearchScreen
import com.zibro.ecommerce.presentation.ui.theme.EcommerceAppTheme
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(googleSignInClient: GoogleSignInClient) {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainHeader(
                    viewModel,
                    navController
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) { innerPadding ->
        MainNavigationScreen(viewModel, navController, innerPadding, googleSignInClient)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHeader(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    TopAppBar(
        title = { Text("Ecommerce App") },
        actions = {
            IconButton(
                onClick = { viewModel.openSearchForm(navController) }
            ) {
                Icon(Icons.Filled.Search, "SearchIcon")
            }
            IconButton(
                onClick = { viewModel.openBasket(navController) }
            ) {
                Icon(Icons.Filled.ShoppingCart, "ShoppingCart")
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?
) {
    val bottomNavigationItems = listOf(
        NavigationItem.MainNav.Home,
        NavigationItem.MainNav.Category,
        NavigationItem.MainNav.MyPage,
        NavigationItem.MainNav.Like,
    )

    NavigationBar {

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = { Icon(item.icon, contentDescription = item.route) },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { item.name }
            )
        }
    }
}

@Composable
fun MainNavigationScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues,
    googleSignInClient: GoogleSignInClient
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = NavigationRouteName.MAIN_HOME,
    ) {
        composable(NavigationRouteName.MAIN_HOME) {
            MainHomeScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            MainCategoryScreen(viewModel = mainViewModel, navController)
        }
        composable(NavigationRouteName.MAIN_MY_PAGE) {
            MyPageScreen(viewModel = mainViewModel, googleSignInClient = googleSignInClient)
        }
        composable(NavigationRouteName.MAIN_LIKE) {
            LikeScreen(navHostController = navController, viewModel = mainViewModel)
        }
        composable(NavigationRouteName.BASKET) {
            BasketScreen(navHostController = navController)
        }
        composable(
            NavigationRouteName.CATEGORY + "/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            val categoryString = it.arguments?.getString("category")
            val category = Gson().fromJson(categoryString, Category::class.java)

            if (category != null) {
                CategoryInfoScreen(
                    navHostController = navController,
                    category = category
                )
            }
        }
        composable(
            route = NavigationRouteName.PRODUCT_DETAIL + "/{product}",
            arguments = listOf(navArgument("product") { type = NavType.StringType })
        ) {
            val productString = it.arguments?.getString("product")

            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
        composable(
            route = NavigationRouteName.SEARCH,
        ) {
            SearchScreen(navController)
        }
    }
}