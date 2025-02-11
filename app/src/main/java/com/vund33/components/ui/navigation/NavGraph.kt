package com.vund33.components.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
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

private val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
    {
        scaleIn(
            animationSpec = tween(500, easing = FastOutSlowInEasing),
            initialScale = 0.9f
        ) + fadeIn(
            animationSpec = tween(200)
        )
    }

private val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
    {
        scaleOut(
            animationSpec = tween(500, easing = FastOutSlowInEasing),
            targetScale = 1.1f
        ) + fadeOut(
            animationSpec = tween(200)
        )
    }

private val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
    {
        scaleIn(
            animationSpec = tween(500, easing = FastOutSlowInEasing),
            initialScale = 1.1f
        ) + fadeIn(
            animationSpec = tween(200)
        )
    }

private val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
    {
        scaleOut(
            animationSpec = tween(500, easing = FastOutSlowInEasing),
            targetScale = 0.9f
        ) + fadeOut(
            animationSpec = tween(200)
        )
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
            route = Screen.HomeScreen.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
        ) {
            HomeScreen(
                onNavigateToComponentScreen = {
                    navActions.navigateToComponentScreen()
                }
            )
        }

        composable(
            route = Screen.ComponentScreen.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            ) {
            ComponentScreen()
        }
    }
}