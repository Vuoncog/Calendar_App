package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.MainScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if (sharedViewModel.getCurrentUser() != null) {
            Graph.MAIN
        } else {
            Graph.AUTHENTICATION
        }
    ) {
        authNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
        composable(route = Graph.MAIN) {
            MainScreen(
                sharedViewModel = sharedViewModel
            )
        }
    }
}


