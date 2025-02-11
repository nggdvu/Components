package com.vund33.components.ui.navigation

import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {

    fun navigateToComponentScreen() {
        navController.navigate(Screen.ComponentScreen.route)
    }
}