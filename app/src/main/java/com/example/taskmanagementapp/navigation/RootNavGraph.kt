package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.ui.screens.home.MainScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavigation(navController = navController)
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}


