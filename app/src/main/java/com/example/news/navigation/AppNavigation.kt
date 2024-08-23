package com.example.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.news.screens.AboutScreen
import com.example.news.screens.NewsScreen
import com.example.news.screens.NewsDetailsScreen
import com.example.news.viewmodel.NewsViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.NewsScreen.route) {

        val viewModel = NewsViewModel()

        composable(route = AppScreens.NewsScreen.route) {
            NewsScreen(navController, viewModel)
        }

        composable(
            route = AppScreens.NewsDetailsScreen.route,
            arguments = listOf(navArgument("title") { type = NavType.StringType })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            title?.let {
                NewsDetailsScreen(navController, title, viewModel)
            }
        }

        composable(route = AppScreens.AboutScreen.route) {
            AboutScreen(navController)
        }
    }
}