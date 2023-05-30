package com.example.taskmanagementapp.navigation.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.data.SharedViewModel
import com.example.taskmanagementapp.ui.screens.profile.ProfileContent

fun NavGraphBuilder.profileNavigation(
    sharedViewModel: SharedViewModel,
    isShowBottomBarItems: (Boolean) -> Unit
){
    navigation(
        startDestination = GraphRoute.Profile.route,
        route = Graph.PROFILE
    ){
        composable(route = GraphRoute.Profile.route){
            isShowBottomBarItems(true)
            ProfileContent(sharedViewModel = sharedViewModel)
        }

    }
}