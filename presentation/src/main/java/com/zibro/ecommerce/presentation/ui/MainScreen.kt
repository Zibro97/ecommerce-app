package com.zibro.ecommerce.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.zibro.ecommerce.presentation.ui.basket.BasketScreen
import com.zibro.ecommerce.presentation.ui.category.CategoryInfoScreen
import com.zibro.ecommerce.presentation.ui.home.LikeScreen
import com.zibro.ecommerce.presentation.ui.home.MainCategoryScreen
import com.zibro.ecommerce.presentation.ui.home.MainHomeScreen
import com.zibro.ecommerce.presentation.ui.home.MyPageScreen
import com.zibro.ecommerce.presentation.ui.product_detail.ProductDetailScreen
import com.zibro.ecommerce.presentation.ui.search.SearchScreen
import com.zibro.ecommerce.presentation.util.NavigationUtils
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
            MainHeader(
                viewModel,
                navController,
                currentRoute
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (MainNav.isMainRoute(currentRoute)) {
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
    navController: NavHostController,
    currentRoute: String?
) {
    TopAppBar(
        title = {
            Text(NavigationUtils.findDestination(currentRoute).title)
        },
        navigationIcon = if(!MainNav.isMainRoute(currentRoute)) {
            {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "backButton")
                }
            }
        } else {
            null
        },
        actions = {
            if (MainNav.isMainRoute(currentRoute)) {
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
        }
    )
}

@Composable
fun MainBottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?
) {
    val bottomNavigationItems = listOf(
        MainNav.Home,
        MainNav.Category,
        MainNav.MyPage,
        MainNav.Like,
    )

    NavigationBar {

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = { Icon(item.icon, contentDescription = item.route) },
                onClick = {
                    NavigationUtils.navigate(
                        controller = navController,
                        routeName = item.route,
                        backStackRouteName = navController.graph.startDestinationRoute
                    )
                },
                label = { item.title }
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
        startDestination = MainNav.Home.route,
    ) {
        composable(
            route = MainNav.Home.route,
            deepLinks = MainNav.Home.deepLinks
        ) {
            MainHomeScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(
            route = MainNav.Category.route,
            deepLinks = MainNav.Category.deepLinks
        ) {
            MainCategoryScreen(viewModel = mainViewModel, navController)
        }
        composable(
            route = MainNav.MyPage.route,
            deepLinks = MainNav.MyPage.deepLinks
        ) {
            MyPageScreen(viewModel = mainViewModel, googleSignInClient = googleSignInClient)
        }
        composable(
            route = MainNav.Like.route,
            deepLinks = MainNav.Like.deepLinks
        ) {
            LikeScreen(navHostController = navController, viewModel = mainViewModel)
        }
        composable(
            route = BasketNav.route,
            deepLinks = BasketNav.deepLinks
        ) {
            BasketScreen(navHostController = navController)
        }
        composable(
            route = SearchNav.route,
            deepLinks = SearchNav.deepLinks
        ) {
            SearchScreen(navController)
        }
        composable(
            route = CategoryNav.routeWithArgName(),
            arguments = CategoryNav.arguments,
            deepLinks = CategoryNav.deepLinks
        ) {
            val category = CategoryNav.findArgument(it)
            if (category != null) {
                CategoryInfoScreen(
                    navHostController = navController,
                    category = category
                )
            }
        }
        composable(
            route = ProductDetailNav.routeWithArgName(),
            arguments = ProductDetailNav.arguments,
            deepLinks = ProductDetailNav.deepLinks
        ) {
            val productString = ProductDetailNav.findArgument(it)
            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
    }
}