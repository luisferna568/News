package com.example.news.navigation

sealed class AppScreens(val route: String) {
    object NewsScreen: AppScreens("news_screen")
    object NewsDetailsScreen: AppScreens("news_screen/{title}") {
        fun createRoute(title: String) = "news_screen/$title"
    }
    object AboutScreen: AppScreens("about")
}