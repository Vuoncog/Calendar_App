package com.example.taskmanagementapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.SystemColorSet
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.MainScreen

fun NavGraphBuilder.mainNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    systemColorSet: SystemColorSet,
    systemColorSetChange: (SystemColorSet) -> Unit
) {
    navigation(
        route = Graph.MAIN,
        startDestination = "main"
    ) {
        composable(route = "main") {
            MainScreen(
                sharedViewModel = sharedViewModel,
                systemColorSet = systemColorSet,
                systemColorSetChange = systemColorSetChange
            )
        }
    }
}