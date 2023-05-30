package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.data.SharedViewModel

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
        mainNavigation(
            navController = navController,
            sharedViewModel = sharedViewModel
        )
    }
}


