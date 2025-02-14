package com.vund33.components.ui.navigation

import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {

    fun navigateToComponentScreen() {
        navController.navigate(Screen.ComponentScreen.route)
    }

    fun navigateToAnimationScreen() {
        navController.navigate(Screen.AnimationScreen.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToAppBarScreen() {
        navController.navigate(Screen.AppBarScreen.route)
    }
}