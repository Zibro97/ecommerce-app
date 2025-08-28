package com.zibro.ecommerce.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zibro.ecommerce.presentation.ui.home.CategoryScreen
import com.zibro.ecommerce.presentation.ui.home.HomeScreen
import com.zibro.ecommerce.presentation.ui.theme.EcommerceAppTheme
import com.zibro.ecommerce.presentation.viewmodel.MainViewModel

sealed class MainNavigationItem(
    val route : String,
    val name : String,
    val icon : ImageVector
) {
    data object Main : MainNavigationItem("Main", "메인", Icons.Filled.Home)
    data object Category : MainNavigationItem("Category", "카테고리", Icons.Filled.Star)
    data object MyPage : MainNavigationItem("MyPage", "마이페이지", Icons.Filled.Person)
}

@Preview(showBackground = true)
@Composable
fun DefaultScreenPreview() {
    EcommerceAppTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { Header(viewModel) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            MainBottomNavigationBar(navController)
        }
    ) { innerPadding ->
        MainNavigationScreen(viewModel,navController, innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    viewModel: MainViewModel
) {
    TopAppBar(
        title = { Text("Ecommerce App") },
        actions = {
            IconButton(
                onClick = { viewModel.openSearchForm() }
            ) {
                Icon(Icons.Filled.Search, "SearchIcon")

            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController : NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage
    )

    NavigationBar{
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

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
    navController : NavHostController,
    innerPadding : PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = MainNavigationItem.Main.route,
    ) {
        composable(MainNavigationItem.Main.route) {
            HomeScreen(viewModel = mainViewModel)
        }
        composable(MainNavigationItem.Category.route) {
            CategoryScreen(viewModel = mainViewModel)
        }
        composable(MainNavigationItem.MyPage.route) {
            Text("마이페이지 화면")
        }
    }
}