package com.example.myapplication.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.BlogViewModel
import com.example.myapplication.ui.screens.BlogListScreen
import com.example.myapplication.ui.screens.BlogDetailScreen

@Composable
fun AppNavigation(blogViewModel: BlogViewModel, contentPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "blogList") {
        composable("blogList") {
            BlogListScreen(navController, blogViewModel, contentPadding)
        }
        composable(
            "blogDetail/{blogUrl}",
            arguments = listOf(navArgument("blogUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            BlogDetailScreen(blogUrl = backStackEntry.arguments?.getString("blogUrl") ?: "")
        }
    }
}
