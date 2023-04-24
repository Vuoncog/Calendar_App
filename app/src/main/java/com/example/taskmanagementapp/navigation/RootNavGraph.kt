package com.example.taskmanagementapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagementapp.ViewModel.LogInViewModel
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.ui.screens.MainScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    logInViewModel: LogInViewModel? = null
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if(logInViewModel?.getCurrentUser() != null){Graph.MAIN}else{Graph.AUTHENTICATION}
    ) {
        authNavigation(
            navController = navController,
            logInViewModel = logInViewModel
        )
        composable(route = Graph.MAIN) {
            MainScreen(logInViewModel = logInViewModel)
        }
    }
}


