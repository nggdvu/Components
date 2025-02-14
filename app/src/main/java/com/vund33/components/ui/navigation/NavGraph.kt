package com.vund33.components.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vund33.components.ui.animations.AnimationScreen
import com.vund33.components.ui.components.ComponentScreen
import com.vund33.components.ui.components.appbar.AppBarScreen
import com.vund33.components.ui.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    data object HomeScreen : Screen("home_screen")
    @Serializable
    data object ComponentScreen : Screen("component_screen")
    @Serializable
    data object AnimationScreen : Screen("animation_screen")
    @Serializable
    data object AppBarScreen : Screen("app_bar_screen")
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    navActions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(
                route = Screen.HomeScreen.route
            ) {
                HomeScreen(
                    onNavigateToComponentScreen = {
                        navActions.navigateToComponentScreen()
                    },
                    onNavigateToAnimationScreen = {
                        navActions.navigateToAnimationScreen()
                    },
                    animatedVisibilityScope = this
                )
            }

            composable(
                route = Screen.ComponentScreen.route
            ) {
                ComponentScreen(
                    onBackClicked = {
                        navActions.navigateBack()
                    },
                    onAppBarCardClicked = {
                        navActions.navigateToAppBarScreen()
                    },
                    animatedVisibilityScope = this
                )
            }

            composable(
                route = Screen.AnimationScreen.route
            ) {
                AnimationScreen()
            }

            composable(
                route = Screen.AppBarScreen.route
            ) {
                AppBarScreen(
                    onBackClicked = {
                        navActions.navigateBack()
                    },
                    animatedVisibilityScope = this
                )
            }
        }
    }
}