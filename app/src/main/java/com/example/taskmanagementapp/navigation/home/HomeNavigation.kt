package com.example.taskmanagementapp.navigation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmanagementapp.constant.Graph
import com.example.taskmanagementapp.constant.GraphRoute
import com.example.taskmanagementapp.constant.RequestState
import com.example.taskmanagementapp.database.Note
import com.example.taskmanagementapp.ui.screens.home.HomeContent
import java.util.*

fun NavGraphBuilder.homeNavigation(
    allTaskInDate: RequestState<Note>,
    currentDate: Date,
    isShowBottomBarItems: (Boolean) -> Unit
){
    navigation(
        startDestination = GraphRoute.Home.route,
        route = Graph.HOME
    ){
        composable(route = GraphRoute.Home.route){
            isShowBottomBarItems(true)
            HomeContent(
                listAllTask = allTaskInDate,
                currentDate = currentDate
            )
        }

    }
}