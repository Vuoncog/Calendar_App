package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.AnimatedSplashScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    systemColorSet: SystemColorSet,
    systemColorSetChange: (SystemColorSet) -> Unit
) {
    val currentUser = if (sharedViewModel.getCurrentUser() != null) {
        Graph.MAIN
    } else {
        Graph.AUTHENTICATION
    }

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        composable(route = Graph.SPLASH) {
            AnimatedSplashScreen(
                navController = navController,
                route = currentUser
            )
        }

        authNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
        )
        mainNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel,
            systemColorSet = systemColorSet,
            systemColorSetChange = systemColorSetChange
        )
    }
}


