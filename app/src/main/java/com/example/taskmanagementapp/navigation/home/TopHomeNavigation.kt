package com.example.taskmanagementapp.navigation.home

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.ui.screens.home.HomeAppBar

fun NavGraphBuilder.topHomeNavigation(

) {
    navigation(
        startDestination = GraphRoute.Home.route,
        route = Graph.HOME
    ) {
        composable(route = GraphRoute.Home.route) {
            Log.e("TOP","2")
            HomeAppBar()
        }
    }
}