package com.vund33.components.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vund33.components.ui.components.ComponentScreen
import com.vund33.components.ui.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    data object HomeScreen : Screen("home_screen")
    @Serializable
    data object ComponentScreen : Screen("component_screen")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    navActions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen()
        }

        composable(
            route = Screen.ComponentScreen.route
        ) {
            ComponentScreen()
        }
    }
}